package com.meli.challenge.views.suggestion.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.meli.challenge.databinding.SearchItemViewHolderBinding
import com.meli.challenge.model.SuggestedQuery

class SuggestionAdapter(
        private var suggestedQueries: List<SuggestedQuery>,
        private var listener: Listener
        ) : RecyclerView.Adapter<SuggestionAdapter.ViewHolder>()  {

    class ViewHolder(
            private val binding: SearchItemViewHolderBinding,
            private val listener: Listener)
        : RecyclerView.ViewHolder(binding.root) {

            fun bind(suggestedQuery: SuggestedQuery){
                binding.itemName.text = suggestedQuery.q
                binding.itemGroup.setOnClickListener { listener.onUserClickSuggestion(suggestedQuery.q) }
                binding.replaceQuery.setOnClickListener { listener.replaceQueryParameter(suggestedQuery.q) }
            }
    }

    fun setList(suggestedQueries: List<SuggestedQuery>) {
        this.suggestedQueries = suggestedQueries
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = SearchItemViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding, listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item: SuggestedQuery = suggestedQueries[position]
        holder.bind(item)
    }

    override fun getItemCount() = suggestedQueries.size

    interface Listener {
        fun onUserClickSuggestion(query:String?)
        fun replaceQueryParameter(query:String?)
    }
}