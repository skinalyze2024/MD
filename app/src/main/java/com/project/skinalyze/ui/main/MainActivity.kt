package com.project.skinalyze.ui.main

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.project.skinalyze.R
import com.project.skinalyze.databinding.ActivityMainBinding
import com.project.skinalyze.ui.ViewModelFactory
import com.project.skinalyze.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var factory: ViewModelFactory
    private val mainViewModel: MainViewModel by viewModels { factory}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupUser()

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_camera,
                R.id.navigation_profile
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }

    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(this)
    }

    private fun setupUser() {
        mainViewModel.getUser().observe(this@MainActivity) {
            mToken = it.token
            if (!it.isLogin) {
                moveActivity()
            }
        }
        showToast()
    }

    private fun moveActivity() {
        startActivity(Intent(this@MainActivity, LoginActivity::class.java))
        finish()
    }

    private fun showToast() {
        mainViewModel.textToast.observe(this) {
            it.getContentIfNotHandled()?.let { message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        var mToken = "token"
    }
}