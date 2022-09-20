package com.quanticheart.tcg.presentation.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.quanticheart.core.extentions.setupNavigation
import com.quanticheart.tcg.R
import com.quanticheart.tcg.databinding.ActivityMainBinding

class HomeActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val navController = findNavController(R.id.container)
        binding.navView.setupNavigation(navController)
    }
}