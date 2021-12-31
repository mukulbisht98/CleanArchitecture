package com.xxmukulxx.notes.util

import android.widget.Toast
import com.xxmukulxx.notes.MyApplication

object Utilities {

    fun showToast(message:String){
        Toast.makeText(MyApplication.context, message, Toast.LENGTH_SHORT).show()
    }


}