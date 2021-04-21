package com.aqube.ram.extension

import android.app.Activity
import android.view.View
import androidx.fragment.app.Fragment
import com.aqube.ram.R
import com.google.android.material.snackbar.Snackbar

internal fun Activity.showSnackBar(view: View, message: String, isError: Boolean = false) {
    val sb = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
    if (isError)
        sb.setBackgroundTint(loadColor(R.color.colorError))
            .setTextColor(loadColor(R.color.white))
            .show()
    else
        sb.setBackgroundTint(loadColor(R.color.colorSecondary))
            .setTextColor(loadColor(R.color.black))
            .show()

}


internal fun Fragment.showSnackBar(view: View, message: String, isError: Boolean = false) {
    val sb = Snackbar.make(view, message, Snackbar.LENGTH_LONG)
    if (isError)
        sb.setBackgroundTint(context!!.loadColor(R.color.colorError))
            .setTextColor(context!!.loadColor(R.color.white))
            .show()
    else
        sb.setBackgroundTint(context!!.loadColor(R.color.colorSecondary))
            .setTextColor(context!!.loadColor(R.color.black))
            .show()

}