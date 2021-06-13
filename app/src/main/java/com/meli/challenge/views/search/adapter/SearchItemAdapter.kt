package com.meli.challenge.views.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meli.challenge.databinding.SearchItemViewHolderBinding
import com.meli.challenge.model.Item

class SearchItemAdapter(private var items: List<Item>) :
    RecyclerView.Adapter<SearchItemAdapter.ViewHolder>()  {

    class ViewHolder(private val itemBinding: SearchItemViewHolderBinding)
        : RecyclerView.ViewHolder(itemBinding.root) {

            fun bind(item: Item){}
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = SearchItemViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: Item = items[position]
        holder.bind(item)
    }

    override fun getItemCount() = items.size

    fun setList(items: List<Item>) {
        this.items = this.items
        notifyDataSetChanged()
    }
}