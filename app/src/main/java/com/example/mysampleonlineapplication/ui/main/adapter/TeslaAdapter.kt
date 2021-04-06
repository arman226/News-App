package com.example.mysampleonlineapplication.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mysampleonlineapplication.data.network.tesla.Article
import com.example.mysampleonlineapplication.databinding.ItemTeslaBinding
import com.example.mysampleonlineapplication.ui.BaseViewHolder

class TeslaAdapter(private var teslaList: List<Article>) : RecyclerView.Adapter<BaseViewHolder>() {

    lateinit var onItemClickListener: OnItemClickListener
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return TeslaViewHolder(
            ItemTeslaBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return teslaList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        return holder.onBind(position)
    }

    inner class TeslaViewHolder(
        private var binding: ItemTeslaBinding
    ) : BaseViewHolder(binding.root) {
        override fun onBind(position: Int) {

            val article = teslaList[position]

            binding.root.setOnClickListener {
                onItemClickListener.onItemClick(position)
            }

            binding.tvTitle.text = article.title
            binding.tvContent.text = article.content
            binding.tvAuthor.text = article.author.toString()

            binding.executePendingBindings()
        }
    }

    interface OnItemClickListener {
        fun onItemClick(selectedIndex: Int)
    }

}