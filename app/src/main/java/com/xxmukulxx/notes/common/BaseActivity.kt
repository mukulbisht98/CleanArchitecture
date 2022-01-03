package com.xxmukulxx.notes.common

import android.app.Dialog
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.xxmukulxx.notes.R

abstract class BaseActivity : AppCompatActivity() {
    private lateinit var binding: ViewDataBinding
    var mProgressDialog: Dialog? = null

    abstract val layoutResId: Int
    abstract fun onLayoutCreated()


    protected fun getBinding(): ViewDataBinding {
        return binding
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
        onLayoutCreated()
    }

    fun showProgress() {
        mProgressDialog?.run {
            show()
        } ?: run {
            mProgressDialog = Dialog(this, android.R.style.ThemeOverlay_Material_Dialog)
            mProgressDialog?.window!!.requestFeature(Window.FEATURE_NO_TITLE)
            mProgressDialog?.window!!.setBackgroundDrawable(ColorDrawable(android.graphics.Color.TRANSPARENT))
            mProgressDialog?.setContentView(R.layout.dialog_loader)
            mProgressDialog?.setCancelable(false)
            mProgressDialog?.show()
        }
    }

    fun hideProgress() {
        mProgressDialog?.run {
            if (isShowing) dismiss()
        }
    }


    fun showToast(message: String, length: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(this, message, length).show()
    }

    /* fun askForPermissions(
         permissions: ArrayList<String>,
         multiplePermissionsListener: MultiplePermissionsListener
     ) {
         Dexter.withContext(this)
             .withPermissions(permissions)
             .withListener(multiplePermissionsListener)
             .onSameThread()
             .check()
     }

     fun displayPermissionDialogToOpenSettings(title: Int, message: Int) {
         var builder: AlertDialog.Builder = AlertDialog.Builder(this)
         builder.setTitle(title)
         builder.setMessage(message)
         builder.setPositiveButton(
             R.string.settings
         ) { dialog, which ->
             ActionUtils.openAppSettings(this)
         }
         builder.setCancelable(true)
         val permissionsAlertDialog = builder.create()
         if (!isFinishing)
             permissionsAlertDialog.show()
     }
 */
    fun displayInfoDialog(title: Int, message: Int, buttonText: Int) {
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton(
            buttonText, null
        )
        builder.setCancelable(true)
        val permissionsAlertDialog = builder.create()
        if (!isFinishing)
            permissionsAlertDialog.show()
    }
}