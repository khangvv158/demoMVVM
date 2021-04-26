package com.sun.demomvvm.data.repository

import com.sun.demomvvm.data.model.Pagination
import com.sun.demomvvm.data.source.api.ServiceAPI
import retrofit2.Response

interface UserRepository {
    suspend fun getUsers(numberPage: Int): Response<Pagination>
}

class UserRepositoryImpl(private val serviceAPI: ServiceAPI) : UserRepository {

    override suspend fun getUsers(numberPage: Int) = serviceAPI.getUsers(numberPage)
}
