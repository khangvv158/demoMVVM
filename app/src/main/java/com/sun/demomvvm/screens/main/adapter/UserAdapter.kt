package com.sun.demomvvm.screens.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sun.demomvvm.R
import com.sun.demomvvm.data.model.User
import com.sun.demomvvm.databinding.ItemLayoutUserBinding
import com.sun.demomvvm.utils.OnItemClickListener

class UserAdapter : ListAdapter<User, UserAdapter.ViewHolder>(UserDiffUtil()) {

    private var itemClickListener: OnItemClickListener<User>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemLayoutUserBinding>(
                LayoutInflater.from(parent.context),
                R.layout.item_layout_user,
                parent,
                false
        )
        return ViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    fun registerItemClickListener(onItemClickListener: OnItemClickListener<User>) {
        this.itemClickListener = onItemClickListener
    }

    class ViewHolder(
            private val biding: ItemLayoutUserBinding,
            private val itemClickListener: OnItemClickListener<User>?,
            private val itemViewModel: UserItemViewModel = UserItemViewModel(itemClickListener)
    ) : RecyclerView.ViewHolder(biding.root) {

        init {
            biding.viewModel = itemViewModel
        }

        fun bindData(user: User) {
            itemViewModel.setData(user)
            biding.executePendingBindings()
        }
    }
}
