package com.sun.demomvvm.screens.main.adapter

import android.view.View
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.sun.demomvvm.BR
import com.sun.demomvvm.data.model.User
import com.sun.demomvvm.utils.OnItemClickListener

class UserItemViewModel(
    private val onItemClickListener: OnItemClickListener<User>? = null
) : BaseObservable() {

    @Bindable
    var user: User? = null

    @Bindable
    var name: String? = null

    fun setData(user: User?) {
        user?.let {
            this.user = it
            notifyPropertyChanged(BR.user)
            name = "${user.firstName} ${user.lastName}"
            notifyPropertyChanged(BR.name)
        }
    }

    fun onItemClicked(view: View) {
        user?.let { onItemClickListener?.onItemClick(it) }
    }
}
