package com.meli.challenge.views.search.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.meli.challenge.R
import com.meli.challenge.databinding.ItemViewHolderBinding
import com.meli.challenge.extensions.loadurl
import com.meli.challenge.extensions.openActivity
import com.meli.challenge.model.Item
import com.meli.challenge.views.specificproduct.SpecificProductActivity
import com.meli.challenge.views.specificproduct.SpecificProductActivity.Companion.PRODUCT_ID

class SearchItemPagingAdapter : PagingDataAdapter<Item,SearchItemPagingAdapter.ViewHolder>(
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
        val itemBinding = ItemViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    class ViewHolder(private val binding: ItemViewHolderBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Item){
            binding.itemImage.loadurl(item.thumbnail)
            binding.itemTitle.text = item.title
            binding.itemPrice.text = binding.root.resources.getString(R.string.price_format, item.price.toString())
            binding.holderContainer.setOnClickListener { item.id?.let {navigateToSpecificProduct(it) } }
        }

        private fun navigateToSpecificProduct(id: String) =
                binding.root.context
                        .openActivity(SpecificProductActivity::class.java) { putString(PRODUCT_ID, id) }
    }

}