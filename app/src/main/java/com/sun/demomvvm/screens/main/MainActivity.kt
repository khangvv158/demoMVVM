package com.sun.demomvvm.screens.main

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sun.demomvvm.R
import com.sun.demomvvm.data.model.User
import com.sun.demomvvm.databinding.ActivityMainBinding
import com.sun.demomvvm.screens.main.adapter.UserAdapter
import com.sun.demomvvm.utils.Constant
import com.sun.demomvvm.utils.OnItemClickListener
import com.sun.demomvvm.utils.Status
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), OnItemClickListener<User> {

    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var binding: ActivityMainBinding
    private val userAdapter by lazy { UserAdapter() }
    private val viewModel: MainActivityViewModel by viewModel()
    private var page = Constant.PAGE_DEFAULT
    private var isLoading = true
    private var pastVisibleItems = 0
    private var visibleItemCount = 0
    private var totalItemCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = viewModel
        initViews()
        registerLiveData()
    }

    private fun initViews() {
        linearLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.userRecyclerView.apply {
            adapter = userAdapter
            layoutManager = linearLayoutManager
        }
        userAdapter.registerItemClickListener(this)
        setupLoadMore()
    }

    private fun setupLoadMore() {
        binding.userRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    visibleItemCount = linearLayoutManager.childCount
                    totalItemCount = linearLayoutManager.itemCount
                    pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition()
                    if (isLoading) {
                        if ((visibleItemCount + pastVisibleItems) >= totalItemCount) {
                            page += 1
                            viewModel.getUsers(page)
                        }
                    }
                }
            }
        })
    }

    private fun registerLiveData() {
        viewModel.users.observe(this, { resource ->
            val data = resource.data
            when (resource.status) {
                Status.SUCCESS -> {
                    data?.let {
                        userAdapter.submitList(it.toMutableList())
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(baseContext, resource.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onItemClick(data: User) {
        Toast.makeText(baseContext, data.email, Toast.LENGTH_SHORT).show()
    }
}
