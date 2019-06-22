package com.mamontov.cattest.screens

import android.content.Intent
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import com.mamontov.cattest.R
import com.mamontov.cattest.screens.ErrorDialogFragment.Builder.Companion.REQUEST_CODE

typealias Args = Bundle

fun argsOf(): Args = Bundle()

fun argsOf(block: Args.() -> Unit) = argsOf().apply(block)

const val FRAGMENT_DIALOG_TAG = "com.mamontov.FragmentDialog"

val BaseFragment.args: Args
    get () = arguments ?: throw IllegalArgumentException("Fragment has no arguments")

val ErrorDialogFragment.args: Args
    get () = arguments ?: throw IllegalArgumentException("DialogFragment has no arguments")

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = false): View =
        LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

val Int.px: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

fun Fragment.openPermissionSettings() {
    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    val uri = Uri.fromParts("package", requireContext().packageName, null)
    intent.data = uri
    startActivity(intent)
}

fun Fragment.showOkFragmentDialog(requestCode: Int = REQUEST_CODE,
                                  message: String,
                                  title: String? = null,
                                  returnBundle: Bundle? = null) {

    val builder = ErrorDialogFragment.Builder(message,
            getString(R.string.ok),
            requestCode)
            .setTitle(title)
            .setReturnBundle(returnBundle)

    showFragmentDialog(builder)
}


fun Fragment.showFragmentDialog(builder: ErrorDialogFragment.Builder) {
    builder.build().also {
        it.show(childFragmentManager, FRAGMENT_DIALOG_TAG)
    }
}


