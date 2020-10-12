package com.triarc.android.lib.support.view.utils

import android.content.Context
import android.content.DialogInterface
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

/**
 * Created by Devanshu Verma on 20 May, 2020
 */
object ViewUtils {

    fun makeToast(context: Context, message: String?, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, duration).show()
    }

    fun showDialog(context: Context, iconId: Int = 0, title: String? = null, msg: String? = null, buttonText: String, listener: DialogInterface.OnClickListener, isCancellable: Boolean = false): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setIcon(iconId)
        builder.setCancelable(isCancellable)
        builder.setPositiveButton(buttonText, listener)
        return builder.show()
    }

    fun showDialog(context: Context, iconId: Int = 0, title: String? = null, msg: String? = null, positiveText: String, negativeText: String, listener: DialogInterface.OnClickListener, isCancellable: Boolean = false): AlertDialog {
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(msg)
        builder.setIcon(iconId)
        builder.setCancelable(isCancellable)
        builder.setPositiveButton(positiveText, listener)
        builder.setNegativeButton(negativeText, listener)
        return builder.show()
    }
}