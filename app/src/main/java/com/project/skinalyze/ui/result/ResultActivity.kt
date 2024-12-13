package com.project.skinalyze.ui.result

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.project.skinalyze.R
import com.project.skinalyze.data.response.PredictionResult
import com.project.skinalyze.databinding.ActivityResultBinding
import com.project.skinalyze.ui.ViewModelFactory
import com.project.skinalyze.ui.camera.CameraActivity
import com.project.skinalyze.ui.camera.CameraViewModel

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private lateinit var factory: ViewModelFactory
    private val resultViewModel: CameraViewModel by viewModels { factory}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        setupViewModel()
        setupUser()
        getPhoto()

        binding.recyclerViewResult.adapter = ListGoogleResultAdapter(emptyList())
        showRecyclerView()
    }

    private fun setupView() {
        binding.toolbarResult.imageViewBack.setOnClickListener {
            val intent = Intent(this@ResultActivity, CameraActivity::class.java)
            startActivity(intent)
        }
    }

    private fun getPhoto() {
        val photoPath = intent.getStringExtra("photoPath")
        val bitmap = BitmapFactory.decodeFile(photoPath)
        binding.imageViewResult.setImageBitmap(bitmap)
    }


    private fun setupViewModel() {
        factory = ViewModelFactory.getInstance(this)
    }

    private fun setupUser() {
        resultViewModel.predictionResponse.observe(this) { predictionResponse ->
            val predictionResult = predictionResponse.predictionResult
            getPredictionResult(predictionResult)
        }
    }

    private fun getPredictionResult(predictionResult: PredictionResult) {
        binding.apply {
            if (predictionResult.prediction != null) {
                textViewResult.text = predictionResult.prediction
            } else {
                textViewResult.text = getString(R.string.prediction_result_error)
            }
            recyclerViewResult.adapter = predictionResult.googleResult?.let {
                ListGoogleResultAdapter(
                    it
                )
            }
        }
    }

    private fun showToast() {
        resultViewModel.textToast.observe(this) {
            it.getContentIfNotHandled()?.let { message ->
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showRecyclerView() {
        binding.recyclerViewResult.apply {
            layoutManager = LinearLayoutManager(this@ResultActivity)
            setHasFixedSize(true)
        }
    }
}