package com.xxmukulxx.notes.util

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.navigation.NavDirections
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
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

fun TextView.spannableString(stringId: Int, startPos: Int, endPos: Int, handleClick: () -> Unit) =
    try {
        val ss = SpannableString(MyApplication.context.getString(stringId))
        val span1: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                handleClick()
            }
        }
        ss.setSpan(span1, startPos, endPos, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        this.apply {
            text = ss
            movementMethod = LinkMovementMethod.getInstance()
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }