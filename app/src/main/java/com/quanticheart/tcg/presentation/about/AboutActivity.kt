package com.quanticheart.tcg.presentation.about

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.quanticheart.tcg.databinding.ActivityMainBinding

class AboutActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}