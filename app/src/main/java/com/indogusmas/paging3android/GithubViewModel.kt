package com.indogusmas.paging3android

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow

class GithubViewModel(
    private val repository: GithubRepository
): ViewModel() {
    private var currentUsername: String? = null
    private var currentSearchResult: Flow<PagingData<Repo>>? = null

    fun  searchRepos(username: String): Flow<PagingData<Repo>>{
        val lastResult = currentSearchResult
        if (lastResult != null){
            return  lastResult
        }
        currentUsername = username
        val newResult = repository.searchRepos(username)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return  newResult
    }
}