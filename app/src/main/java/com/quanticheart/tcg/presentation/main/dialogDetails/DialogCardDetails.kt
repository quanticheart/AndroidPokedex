package com.quanticheart.tcg.presentation.main.dialogDetails

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.quanticheart.core.base.dialog.BaseFragmentDialog
import com.quanticheart.domain.model.pokemon.Pokemon
import com.quanticheart.tcg.R
import com.quanticheart.tcg.databinding.DialogCardDetailBinding
import com.squareup.picasso.Picasso

//
// Created by Jonn Alves on 15/10/22.
//
class DialogCardDetails(
    private val mContext: Context,
    private val picasso: Picasso,
    private val pokemon: Pokemon
) :
    BaseFragmentDialog<DialogCardDetailBinding>(mContext, DialogCardDetailBinding::inflate),
    Animation.AnimationListener {

    private var animation1: Animation? = null
    private var animation2: Animation? = null
    private var isBackOfCardShowing = true

    override fun view(binding: DialogCardDetailBinding): Unit = binding.run {
        animation1 = AnimationUtils.loadAnimation(mContext, R.anim.hide_card)
        animation1?.setAnimationListener(this@DialogCardDetails)
        animation2 = AnimationUtils.loadAnimation(mContext, R.anim.show_card)
        animation2?.setAnimationListener(this@DialogCardDetails)

        root.setOnClickListener {
            flipCard()
        }
        flipCard()
    }

    private fun flipCard() {
//        binding.root.isEnabled = false
        binding.image.apply {
            clearAnimation()
            animation = animation1
            startAnimation(animation1)
        }
    }

    override fun onAnimationEnd(animation: Animation): Unit = binding.run {
        if (animation === animation1) {
            if (isBackOfCardShowing) {
                picasso
                    .load(pokemon.imageURL)
                    .placeholder(R.drawable.placeholder)
                    .into(image)
            } else {
                image.setImageResource(R.drawable.placeholder)
            }
            image.clearAnimation()
            image.animation = animation2
            image.startAnimation(animation2)
        } else {
            isBackOfCardShowing = !isBackOfCardShowing
//            image.isEnabled = true
        }
    }

    override fun onAnimationRepeat(p0: Animation?) {
    }

    override fun onAnimationStart(p0: Animation?) {
    }
}
