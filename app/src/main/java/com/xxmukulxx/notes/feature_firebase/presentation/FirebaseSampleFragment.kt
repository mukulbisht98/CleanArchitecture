package com.xxmukulxx.notes.feature_firebase.presentation

import android.util.Log
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.xxmukulxx.notes.R
import com.xxmukulxx.notes.common.BaseActivity

class FirebaseSampleFragment(override val layoutResId: Int = R.layout.frag_firebase) :
    BaseActivity() {

    private fun firebase() {
        FirebaseDynamicLinks.getInstance()
            .getDynamicLink(intent).addOnSuccessListener {
                Log.e("FIREBASE SUCCESS ---->", "firebase: ${it.link}")
            }
            .addOnFailureListener {
                Log.e("FIREBASE ERROR ---->", "firebase: ${it.message}")
            }
    }

    override fun onLayoutCreated() {
        firebase()
    }
}