package com.xxmukulxx.notes.util

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.findFragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.xxmukulxx.notes.MyApplication.AppContext.appContext
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.data.data_store.vm.DataStoreViewModel
import com.xxmukulxx.notes.feature_product.domain.model.ProductData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.File
import java.util.*
import android.provider.MediaStore





// NavigationComponents Utils
fun View.navigateWithId(id: Int, bundle: Bundle? = null, extras: Navigator.Extras? = null) = try {
    this.findNavController().navigate(id, bundle, null, extras)
} catch (e: Exception) {
    e.printStackTrace()
}

fun View.navigateFromSplash(id: Int, bundle: Bundle? = null, extras: Navigator.Extras? = null) = try {
    this.findNavController().navigate(id, bundle, NavOptions.Builder()
        .setPopUpTo(R.id.splashFragment,
            true).build(), extras)
} catch (e: Exception) {
    e.printStackTrace()
}

fun View.navigateWithAction(action: NavDirections) = try {
    this.findNavController().navigate(action)
} catch (e: Exception) {
    e.printStackTrace()
}

fun View.navigateWithViewModel(id: Int) = try {
    NavHostFragment.findNavController(this.findFragment()).navigate(id)
} catch (e: Exception) {
    e.printStackTrace()
}

fun View.navigateBack() = try {
    this.findNavController().navigateUp()
} catch (e: Exception) {
    e.printStackTrace()
}

fun getNavOptions(): NavOptions {
    return NavOptions.Builder()
        .setEnterAnim(R.anim.back_pop_exit)
        .setExitAnim(R.anim.login_pop_back)
        .build()
}

// TextView Utils
fun TextView.spannableString(stringId: Int, startPos: Int, endPos: Int, handleClick: () -> Unit) =
    try {
        val ss = SpannableString(getString(stringId))
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                handleClick()
            }
        }
        ss.setSpan(clickableSpan, startPos, endPos, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        ss.setSpan(
            ForegroundColorSpan(getColor(R.color.day_theme_700)),
            startPos,
            endPos,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        this.apply {
            text = ss
            movementMethod = LinkMovementMethod.getInstance()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }

// Visibility Utils
fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.invisible() {
    this.visibility = View.INVISIBLE
}

// DarkMode Utils
fun toggleDarkMode(dataStoreViewModel: DataStoreViewModel, activity: Activity) {
    CoroutineScope(Dispatchers.Main).launch {
        dataStoreViewModel.readFromLocal.collect {
            when (it) {
                1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                2 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                3 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                else -> dataStoreViewModel.saveToLocal(3)
            }
//            activity.setStatusBarGradiant(it)
        }
    }
}

/*~~~~~~~~~Coroutine Run On IO Thread~~~~~~~~~~~~~~~~~~~*/
fun runOnIO(run: () -> Unit) {
    CoroutineScope(Dispatchers.IO).launch {
        run()
    }
}

// Keyboard Utils
fun hideSoftKeyboard(activity: Activity) {
    val inputMethodManager =
        activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    var view = activity.currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(activity)
    }
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}

// Toast Utils
fun toast(message: String) {
    Toast.makeText(appContext, message, Toast.LENGTH_SHORT).show()
}


// String Utils
fun String.capitalize(): String {
    return this.first().uppercase() + this.substring(1)
}

// Resources Utils
fun getString(id: Int): String {
    return appContext.getString(id)
}

//Color Utils
fun getColor(id: Int): Int {
    return appContext.getColor(id)
}

// Glide Utils
fun ImageView.setImgProfile(url: String?) {
    if (url.isNullOrEmpty()) {
        Glide.with(appContext).load(R.drawable.ic_profile)
            .circleCrop()
            .into(this)
    } else {
        Glide.with(appContext).load(url)
            .error(R.drawable.ic_profile)
            .thumbnail(Glide.with(appContext).load(R.drawable.loader_gif).circleCrop())
            .placeholder(R.drawable.ic_profile)
            .circleCrop()
            .into(this)
    }
}

fun ImageView.setImg(url: String?) {
    if (url.isNullOrEmpty()) {
        Glide.with(appContext).load(R.drawable.ic_image_placeholder)
            .into(this)
    } else {
        Glide.with(appContext).load(url).transform(CenterCrop())
            .error(R.drawable.ic_image_placeholder)
            .thumbnail(Glide.with(appContext).load(R.drawable.loader_gif))
            .placeholder(R.drawable.ic_image_placeholder)
            .into(this)
    }
}
fun ImageView.setImgFromUri(uri: Uri) {
        Glide.with(appContext).load(uri)
            .error(R.drawable.ic_image_placeholder)
            .thumbnail(Glide.with(appContext).load(R.drawable.loader_gif))
            .placeholder(R.drawable.ic_image_placeholder)
            .into(this)
}

fun ImageView.setImgWithRadius(url: String?, radius: Int) {
    if (url.isNullOrEmpty()) {
        Glide.with(appContext).load(R.drawable.ic_image_placeholder)
            .into(this)
    } else {
        Glide.with(appContext).load(url).transform(CenterCrop(), RoundedCorners(radius))
            .error(R.drawable.ic_image_placeholder)
            .thumbnail(Glide.with(appContext).load(R.drawable.loader_gif))
            .placeholder(R.drawable.ic_image_placeholder)
            .into(this)
    }
}

val Context.dataStore by preferencesDataStore(name = DATA_STORE_NAME)

// status bar
fun Activity.setStatusBarGradiant(i: Int) {
    val window: Window = this.window
    var ui: Int = -1
    ui = when (i) {
        1 -> R.drawable.bg_toolbar
        2 -> R.drawable.bg_toolbar_night
        else -> {
            when (resources.configuration.uiMode) {
                Configuration.UI_MODE_NIGHT_YES -> R.drawable.bg_toolbar_night
                Configuration.UI_MODE_NIGHT_NO -> R.drawable.bg_toolbar
                else -> R.drawable.bg_toolbar_night
            }
        }
    }
    val background = ContextCompat.getDrawable(appContext, ui)
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = getColor(android.R.color.transparent)
    window.navigationBarColor = getColor(android.R.color.transparent)
    window.setBackgroundDrawable(background)
}

/*
    * Local Search
    *
    * */
fun filter(models: ArrayList<Any>, query: String): ArrayList<Any> {
    val lowerCaseQuery = query.lowercase(Locale.getDefault())
    val filteredModelList = ArrayList<Any>()
    for (i in 0 until models.size) {
        if (models[i] is ProductData) {
            val text = (models[i] as ProductData).title?.lowercase(Locale.getDefault())
            if (text!!.contains(lowerCaseQuery)) {
                filteredModelList.add(models[i])
            }
        }
    }
    return filteredModelList
}

/*
    *
    * To get image in multipart body
    * */
fun convertImageInMultipart(imagePath: String, thumbImage: String): Pair<MultipartBody.Part, MultipartBody.Part> {
    val file = File(imagePath)
    val requestBody = RequestBody.create("*/*".toMediaTypeOrNull(), file);
    val fileToUpload = MultipartBody.Part.createFormData("image", file.getName(), requestBody);
    val thumbFile = File(thumbImage)
    val requestBodyThumb = RequestBody.create("*/*".toMediaTypeOrNull(), thumbFile);
    val fileToUploadThumb = MultipartBody.Part.createFormData(
        "image_thumb",
        thumbFile.getName(),
        requestBodyThumb
    );
    return  Pair(fileToUpload, fileToUploadThumb)
}

fun getPathFromURI(context: Context,contentUri: Uri?): String? {
    var res: String? = null
    val proj = arrayOf(MediaStore.Images.Media.DATA)
    val cursor: Cursor =
        contentUri?.let { context.contentResolver.query(it, proj, null, null, null) }!!
    if (cursor.moveToFirst()) {
        val column_index: Int = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        res = cursor.getString(column_index)
    }
    cursor.close()
    return res
}