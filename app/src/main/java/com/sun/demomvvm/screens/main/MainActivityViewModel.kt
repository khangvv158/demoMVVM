package com.sun.demomvvm.screens.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sun.demomvvm.data.model.Pagination
import com.sun.demomvvm.data.model.User
import com.sun.demomvvm.data.repository.UserRepository
import com.sun.demomvvm.utils.Constant.PAGE_DEFAULT
import com.sun.demomvvm.utils.Resource
import com.sun.demomvvm.utils.plusAssign
import kotlinx.coroutines.launch

class MainActivityViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _users = MutableLiveData<Resource<MutableList<User>>>()
    val users: MutableLiveData<Resource<MutableList<User>>>
        get() = _users

    init {
        getUsers()
    }

    fun getUsers(numberPage: Int = PAGE_DEFAULT) {
        viewModelScope.launch {
            try {
                userRepository.getUsers(numberPage).apply {
                    body()?.let {
                        if (it.data.isNotEmpty()) {
                            _users.plusAssign(it.data)
                        }
                    }
                }
            } catch (e: Exception) {
                _users.postValue(Resource.error(null, e.localizedMessage))
            }
        }
    }
}
