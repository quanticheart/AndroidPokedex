package com.quanticheart.core.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ViewFlipper
import androidx.core.content.withStyledAttributes
import com.quanticheart.core.R
import com.quanticheart.core.databinding.LayoutErrorBinding
import com.quanticheart.core.databinding.LayoutLoadingBinding

class ControlLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null
) : ViewFlipper(context, attrs) {

    private var callback: (() -> Unit)? = null
    private var error: LayoutErrorBinding? = null

    init {
        val loading = LayoutLoadingBinding.inflate(LayoutInflater.from(context))
        error = LayoutErrorBinding.inflate(LayoutInflater.from(context))

        context.withStyledAttributes(attrs, R.styleable.ControlLayout) {
            getString(R.styleable.ControlLayout_errorMsg)?.let {
                error?.tvError?.text = it
            }
        }

        error?.btRetry?.setOnClickListener { callback?.let { it1 -> it1() } }
        addView(loading.root)
        addView(error?.root)
    }

    fun showLoading() {
        displayedChild = 0
    }

    fun showError(message: String? = null) {
        displayedChild = 1
        message?.let { error?.tvError?.text = it }
    }

    fun showLayout() {
        displayedChild = 2
    }

    fun setTryReloadCallback(callback: () -> Unit) {
        this.callback = callback
    }
}