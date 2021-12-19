package com.indogusmas.paging3android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.indogusmas.paging3android.databinding.ActivityMainBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var searchJob: Job? = null
    private lateinit var viewModel: GithubViewModel
    private lateinit var adapter: ReposAdapter
    private  val  TAG : String = javaClass.simpleName


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this,Injection.provideViewModelFactory(this))
            .get(GithubViewModel::class.java)

        adapter = ReposAdapter()
        search("")
        val layoutManager = LinearLayoutManager(applicationContext)
        binding.rvRepo.layoutManager = layoutManager
        binding.rvRepo.adapter = adapter

    }

    private  fun search(productName: String){
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchRepos(productName).collect {
                Log.d(TAG, "search: "+ it)
                adapter.submitData(it)
            }
        }
    }
}

