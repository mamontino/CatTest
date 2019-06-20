package com.mamontov.cattest.screens

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


fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View =
    LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

val Int.dp: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Fragment.createDialog(@StringRes message: Int, listener: () -> Unit) {
    val builder = AlertDialog.Builder(requireActivity())

    with(builder)
    {
        setMessage(message)
        setCancelable(false)
        setPositiveButton(requireActivity().resources.getText(com.mamontov.cattest.R.string.ok)) { _, _ -> listener }
        show()
    }
}

fun Fragment.createDialog(message: String, listener: () -> Unit) {
    val builder = AlertDialog.Builder(requireActivity())

    with(builder)
    {
        setMessage(message)
        setCancelable(false)
        setPositiveButton(requireActivity().resources.getText(com.mamontov.cattest.R.string.ok)) { _, _ -> listener }
        show()
    }
}

fun Fragment.openPermissionSettings() {
    val intent = Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.parse("package:" + requireActivity().packageName)
    )
    intent.addCategory(Intent.CATEGORY_DEFAULT)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
}