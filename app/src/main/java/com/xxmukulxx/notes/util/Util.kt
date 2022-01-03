package com.xxmukulxx.notes.util

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.TextView
import androidx.fragment.app.findFragment
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.xxmukulxx.notes.MyApplication
import com.xxmukulxx.notes.R


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
        .setEnterAnim(R.anim.login_enter)
        .setExitAnim(R.anim.login_pop_enter)
        .setPopExitAnim(R.anim.back_exit)
        .setPopEnterAnim(R.anim.back_pop_exit)
        .build()
}

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
            ForegroundColorSpan(getColor(R.color.teal_700)),
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

fun getString(id: Int): String {
    return MyApplication.context.getString(id)
}

fun getColor(id: Int): Int {
    return MyApplication.context.getColor(id)
}
