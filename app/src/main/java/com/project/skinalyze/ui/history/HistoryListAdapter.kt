package com.project.skinalyze.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.skinalyze.R
import com.project.skinalyze.data.response.HistoryResponseItem
import com.project.skinalyze.databinding.ItemRowHistoryBinding

class HistoryListAdapter(private val listHistory: List<HistoryResponseItem>): RecyclerView.Adapter<HistoryListAdapter.ListViewHolder>() {

    inner class ListViewHolder(private val binding: ItemRowHistoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(history: HistoryResponseItem) {
            binding.apply {
                textViewTime.text = history.datetime
                textViewName.text = history.prediction
                Glide.with(itemView.context)
                    .load(history.imageUrl)
                    .error(R.drawable.outline_account_circle)
                    .into(imageViewImage)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemRowHistoryBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listHistory[position])
    }

    override fun getItemCount(): Int = listHistory.size
}