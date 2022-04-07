package com.hexalitics.mvvm_starter.utils

import android.app.Dialog
import android.view.View
import android.view.Window
import com.google.android.material.snackbar.Snackbar
import com.hexalitics.mvvm_starter.R

object AppProgressDialog {

    fun show(mProgressDialog: Dialog) {
        try {
            if (mProgressDialog.isShowing) return
            mProgressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            mProgressDialog.setContentView(R.layout.layout_progress_bar)
            // ((TextView) mProgressDialog.findViewById(R.id.title)).setText(msg);
            mProgressDialog.setCancelable(false)
            if (mProgressDialog.window != null) mProgressDialog.window!!.setBackgroundDrawableResource(
                android.R.color.transparent
            )
            mProgressDialog.show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun hide(mProgressDialog: Dialog?) {
        if (mProgressDialog != null && mProgressDialog.isShowing) {
            mProgressDialog.dismiss()
        }
    }
}