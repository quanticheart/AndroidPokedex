package com.quanticheart.core.components

import android.content.Context
import android.text.InputType
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.core.content.withStyledAttributes
import androidx.core.widget.addTextChangedListener
import com.quanticheart.core.R
import com.quanticheart.core.databinding.ComponentContainerTextBinding

class TextContainer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : FrameLayout(context, attrs, defStyleAttr) {

    private var inputType = 2

    private val binding =
        ComponentContainerTextBinding.inflate(LayoutInflater.from(context), this, true)

    private var callback: ((String) -> Unit)? = null

    init {
        context.withStyledAttributes(attrs, R.styleable.TextContainer) {
            val value = getString(R.styleable.TextContainer_title) ?: ""
            binding.title.text = value

            val placeholder = getString(R.styleable.TextContainer_placeholder) ?: ""
            binding.editText.hint = placeholder

            inputType = getInt(R.styleable.TextContainer_inputType, inputType)
        }

        binding.editText.apply {
            addTextChangedListener {
                it?.toString()?.let { text -> callback?.let { send -> send(text) } }
            }

            when (inputType) {
                0 -> inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                1 -> inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                2 -> inputType =
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS
            }
        }
    }

    fun addTextChangedListener(callback: (String) -> Unit) {
        this.callback = callback
    }

    val text: String
        get() = binding.editText.text.toString()
}