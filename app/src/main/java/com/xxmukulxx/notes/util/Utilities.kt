package com.xxmukulxx.notes.util

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.xxmukulxx.notes.MyApplication

object Utilities {

    fun showToast(message:String){
        Toast.makeText(MyApplication.context, message, Toast.LENGTH_SHORT).show()
    }

    //Hide Keyboard
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

}