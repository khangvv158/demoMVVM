package com.sun.demomvvm.utils

import androidx.lifecycle.MutableLiveData

fun <T> MutableLiveData<Resource<MutableList<T>>>.plusAssign(items: MutableList<T>) {
    val value = this.value?.data ?: mutableListOf()
    value.addAll(items)
    postValue(Resource.success(value))
}
