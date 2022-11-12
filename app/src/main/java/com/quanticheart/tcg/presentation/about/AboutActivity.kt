package com.quanticheart.tcg.presentation.about

import com.quanticheart.core.base.activity.BaseActivity
import com.quanticheart.core.extentions.setBackToolbar
import com.quanticheart.tcg.R
import com.quanticheart.tcg.databinding.ActivityAboutBinding
import com.quanticheart.tcg.observeStateLayout
import com.squareup.picasso.Picasso
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class AboutActivity :
    BaseActivity<AboutViewModel, ActivityAboutBinding>(ActivityAboutBinding::inflate) {

    override val viewModel: AboutViewModel by viewModel()
    private val picasso: Picasso by inject()

    override fun view(binding: ActivityAboutBinding): Unit = binding.layout.run {
        navigationBar.setBackToolbar(getString(R.string.label_about))
    }

    override fun viewModel(viewModel: AboutViewModel): Unit = viewModel.run {
        details.observeStateLayout(this@AboutActivity, binding.flipper) {
            binding.layout.run {
                picasso.load(it.img)
                    .placeholder(R.drawable.placeholder)
                    .into(img)
                description.text = it.desc
                devs.text = it.devs.joinToString(separator = "\n") { it }
            }
        }
        getDetails()
    }
}