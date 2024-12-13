package com.project.skinalyze.ui.result

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.skinalyze.databinding.ActivityGoogleResultBinding

class GoogleResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGoogleResultBinding

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGoogleResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            val url = intent.getStringExtra("url")
            webView.loadUrl("$url")
            webView.settings.javaScriptEnabled = true
        }
        setupView()
    }

    private fun setupView() {
        binding.toolbarGoogleResult.imageViewBack.setOnClickListener{
            finish()
        }
    }
}