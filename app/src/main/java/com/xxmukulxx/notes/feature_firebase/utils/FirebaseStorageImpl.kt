package com.xxmukulxx.notes.feature_firebase.utils

import android.net.Uri
import android.util.Log
import com.google.firebase.storage.FirebaseStorage
import com.xxmukulxx.notes.util.toast
import java.util.*

class FirebaseStorageImpl {
    companion object {
        const val TAG = "FIREBASE_STORAGE ->"
    }

    var firebaseStorage = FirebaseStorage.getInstance()
    var storageRef = firebaseStorage.reference.child("images/" + UUID.randomUUID())


    fun uploadImageToFirebase(uri: Uri) {
        storageRef.putFile(uri).addOnSuccessListener {
            toast("Successfully uploaded!")
        }.addOnFailureListener {
            Log.e(TAG, "uploadImageToFirebase: ${it.message}")
        }.addOnProgressListener {

        }
    }
}