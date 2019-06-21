package com.mamontov.cattest.screens

import android.content.DialogInterface
import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.mamontov.cattest.R


fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View =
        LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Fragment.createDialog(@StringRes message: Int, positiveButtonClick: (DialogInterface, Int) -> Unit) {
    val builder = AlertDialog.Builder(requireActivity())
    val alert = builder.create()

    with(builder)
    {
        setMessage(message)
        setCancelable(false)
        setPositiveButton(requireActivity().resources.getText(R.string.ok))
        { _, _ -> DialogInterface.OnClickListener(function = positiveButtonClick)
            alert.cancel()}
        show()
    }
}

fun Fragment.createDialog(message: String, positiveButtonClick: (DialogInterface, Int) -> Unit) {
    val builder = AlertDialog.Builder(requireActivity())
    val alert = builder.create()

    with(builder)
    {
        setMessage(message)
        setCancelable(false)
        setPositiveButton(requireActivity().resources.getText(R.string.ok)) { _, _ ->
            DialogInterface.OnClickListener(function = positiveButtonClick)
            alert.cancel()
        }
        show()
    }
}

fun Fragment.openPermissionSettings() {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    val uri = Uri.fromParts("package", requireActivity().packageName, null)
    intent.data = uri
    startActivity(intent)
}