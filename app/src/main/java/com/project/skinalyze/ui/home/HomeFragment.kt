package com.project.skinalyze.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.project.skinalyze.R
import com.project.skinalyze.data.response.UserData
import com.project.skinalyze.databinding.FragmentHomeBinding
import com.project.skinalyze.model.ArticleData
import com.project.skinalyze.ui.ViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel by viewModels<HomeViewModel> {
        ViewModelFactory.getInstance(requireContext())
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        setupUser()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerViewArticle.adapter = ArticleListAdapter(ArticleData.article)
        showRecyclerView()
    }

    private fun setupUser() {
        homeViewModel.getUser().observe(viewLifecycleOwner) {
            setProfileData(it.token)
        }
        homeViewModel.userResponse.observe(viewLifecycleOwner) {
            val userData = it.userData
            getUserData(userData)
        }
    }

    private fun getUserData(userData: UserData) {
        binding.apply {
            Glide.with(requireContext())
                .load(userData.avatarUrl)
                .error(R.drawable.round_account_box_24)
                .into(imageViewAvatar)
            textViewInitial.text = userData.name
        }
    }

    private fun setProfileData(token: String) {
        homeViewModel.getUserData(token)
    }

    private fun showRecyclerView() {
        binding.recyclerViewArticle.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}