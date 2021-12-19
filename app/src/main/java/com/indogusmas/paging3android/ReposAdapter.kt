package com.indogusmas.paging3android

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.indogusmas.paging3android.databinding.ItemRepoBinding

class ReposAdapter : PagingDataAdapter<Repo, ReposAdapter.ViewHolder>(COMPARATOR) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder (
        ItemRepoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )


    class ViewHolder(
        private  val  binding: ItemRepoBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun  bind(repo: Repo) = with(binding){
            tvName.text = repo.itemname
        }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<Repo>() {
            override fun areItemsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                oldItem.itemname == newItem.itemname

            override fun areContentsTheSame(oldItem: Repo, newItem: Repo): Boolean =
                oldItem == newItem
        }
    }
}