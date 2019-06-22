package com.mamontov.cattest.screens

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDialogFragment
import com.mamontov.cattest.R
import kotlinx.android.synthetic.main.dialog_fragment.view.*


private var Args.title: String?
    get() = getString(DIALOG_TITLE, null)
    set(value) = putString(DIALOG_TITLE, value)

private var Args.message: String
    get() = getSerializable(DIALOG_MESSAGE) as String
    set(value) = putSerializable(DIALOG_MESSAGE, value)

private var Args.ownerRequestCode: Int
    get() = getInt(OWNER_REQUEST_CODE)
    set(value) = putInt(OWNER_REQUEST_CODE, value)

private var Args.positiveButtonText: String?
    get() = getString(POSITIVE_BUTTON_TEXT)
    set(value) = putString(POSITIVE_BUTTON_TEXT, value)

private var Args.cancelable: Boolean
    get() = getBoolean(DIALOG_CANCELABLE)
    set(value) = putBoolean(DIALOG_CANCELABLE, value)

private var Args.returnBundle: Bundle?
    get() = getParcelable(RETURN_BUNDLE)
    set(value) = putParcelable(RETURN_BUNDLE, value)

private const val DIALOG_TITLE = "DIALOG_TITLE"
private const val OWNER_REQUEST_CODE = "OWNER_REQUEST_CODE"
private const val DIALOG_MESSAGE = "DIALOG_MESSAGE"
private const val POSITIVE_BUTTON_TEXT = "POSITIVE_BUTTON_TEXT"
private const val DIALOG_CANCELABLE = "DIALOG_CANCELABLE"
private const val RETURN_BUNDLE = "RETURN_BUNDLE"

class ErrorDialogFragment : AppCompatDialogFragment() {

    companion object {
        private fun newInstance(title: String?,
                                message: String,
                                positiveButtonText: String,
                                cancelable: Boolean,
                                returnBundle: Bundle?,
                                ownerRequestCode: Int): ErrorDialogFragment =

                ErrorDialogFragment().apply {
                    arguments = argsOf {
                        this.title = title
                        this.message = message
                        this.positiveButtonText = positiveButtonText
                        this.cancelable = cancelable
                        this.returnBundle = returnBundle
                        this.ownerRequestCode = ownerRequestCode
                    }
                }
    }

    private val ownerFragment: BaseFragment?
        get() = parentFragment as? BaseFragment

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
            Dialog(requireContext()).apply {
                setCanceledOnTouchOutside(args.cancelable)
            }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_fragment, null, false)
        initViews(dialogView)

        return dialogView
    }

    private fun initViews(parent: View) {
        setupTitle(parent)
        setupMessage(parent)
        setupPositiveButton(parent)
    }

    private fun setupTitle(parent: View) {
        if (args.title.isNullOrBlank()) {
            parent.dialogTitle.visibility = View.GONE
        } else {
            parent.dialogTitle.text = args.title
        }
    }

    private fun setupMessage(parent: View) {
        parent.dialogMessage.text = args.message
    }

    private fun setupPositiveButton(parent: View) {
        parent.positiveButton.text = args.positiveButtonText
        parent.positiveButton.setOnClickListener { onPositiveButtonClick() }
    }

    override fun onStart() {
        super.onStart()
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.WRAP_CONTENT
        dialog?.window?.setLayout(width, height)
    }

    private fun onPositiveButtonClick() {
        dismiss()
        ownerFragment?.onPositiveButtonClick(args.ownerRequestCode, args.returnBundle)
    }

    class Builder(private val message: String,
                  private val positiveButtonText: String,
                  private val ownerRequestCode: Int) {

        companion object {
            const val REQUEST_CODE = 1234
        }

        var title: String? = null
            private set

        var cancelable: Boolean = true
            private set

        var returnBundle: Bundle? = null
            private set

        fun setTitle(text: String?): Builder =
                apply { title = text }

        fun setCancelable(value: Boolean): Builder =
                apply { cancelable = value }

        fun setReturnBundle(bundle: Bundle?): Builder =
                apply { returnBundle = bundle }

        fun build(): ErrorDialogFragment =
                newInstance(
                        title,
                        message,
                        positiveButtonText,
                        cancelable,
                        returnBundle,
                        ownerRequestCode)
    }
}