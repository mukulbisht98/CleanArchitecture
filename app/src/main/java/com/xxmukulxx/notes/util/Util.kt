package com.xxmukulxx.notes.util

import android.app.Activity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.findFragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide
import com.xxmukulxx.notes.MyApplication.AppContext.appContext
import com.xxmukulxx.notes.R

// NavigationComponents Utils
fun View.navigateWithId(id: Int, bundle: Bundle? = null) = try {
    this.findNavController().navigate(id, bundle)
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
fun toggleDarkMode(active: Boolean?) {
    active?.let {
        if (it) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    } ?: run {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
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