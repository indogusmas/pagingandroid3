package com.indogusmas.paging3android

import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner

class ViewModelFactory(
    owner: SavedStateRegistryOwner,
    private val repository: GithubRepository) :AbstractSavedStateViewModelFactory(owner,null) {
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(GithubViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GithubViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
