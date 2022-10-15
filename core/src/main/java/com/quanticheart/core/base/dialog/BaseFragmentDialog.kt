package com.quanticheart.core.base.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.InsetDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.viewbinding.ViewBinding
import com.quanticheart.core.base.fragment.Inflate
import com.quanticheart.core.extentions.logI
import com.quanticheart.core.extentions.toDp

abstract class BaseFragmentDialog<VB : ViewBinding>(
    private val mContext: Context,
    private val inflate: Inflate<VB>
) : DialogFragment(), OnDialogActions<VB> {

    private lateinit var fm: FragmentManager
    private var _binding: VB? = null
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding?.let { view(binding) }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val d = super.onCreateDialog(savedInstanceState)
        d.apply {
            setOnShowListener {
                onShow()
            }
        }
        return d
    }

    override fun onStart() {
        super.onStart()
        dialog?.apply {
            val window = window
            window?.apply {
                val padding = 16.toDp
                setBackgroundDrawable(
                    InsetDrawable(
                        ColorDrawable(Color.TRANSPARENT),
                        padding,
                        padding,
                        padding,
                        padding
                    )
                )
                setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }

            setOnDismissListener {
                onHide()
                hideDialog()
            }
        }
    }

    fun show() {
        try {
            (mContext as AppCompatActivity?)?.supportFragmentManager?.let {
                fm = it
                show(it, mContext.javaClass.name)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun dismiss() {
        hideDialog()
    }

    private fun hideDialog() {
        try {
            fm.beginTransaction().remove(this@BaseFragmentDialog)
                .addToBackStack(null).commit()
        } catch (_: Exception) {
        }
    }

    override fun onShow() {
        this.javaClass.simpleName.logI(DialogConstants.TAG_DIALOG_SHOW)
    }

    override fun onHide() {
        this.javaClass.simpleName.logI(DialogConstants.TAG_DIALOG_HIDE)
    }
}
