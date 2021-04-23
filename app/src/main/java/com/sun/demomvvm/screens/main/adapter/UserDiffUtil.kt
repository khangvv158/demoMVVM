package com.sun.demomvvm.screens.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.sun.demomvvm.data.model.User

class UserDiffUtil : DiffUtil.ItemCallback<User>() {

    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}
