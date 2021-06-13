package com.meli.challenge.views.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.meli.challenge.databinding.SearchItemViewHolderBinding
import com.meli.challenge.model.Item

class SearchItemPagingAdapter()
    : PagingDataAdapter<Item,SearchItemPagingAdapter.ViewHolder>(
        object : DiffUtil.ItemCallback<Item>() {
            override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean =
                    oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean =
                    oldItem == newItem
        }
    ){

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let {
            item -> holder.bind(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = SearchItemViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    class ViewHolder(private val binding: SearchItemViewHolderBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item){
            binding.itemName.text = item.title
        }
    }

}