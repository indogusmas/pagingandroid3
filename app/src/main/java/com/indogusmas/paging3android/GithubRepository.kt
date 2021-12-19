package com.indogusmas.paging3android

import androidx.paging.Pager
import androidx.paging.PagingConfig

class GithubRepository(
    private val  api: GithubApi
) {
    fun  searchRepos(username: String) = Pager(
        pagingSourceFactory = {GithubRepoPagingSource(api,username)},
        config = PagingConfig(
            pageSize = 20
        )
    ).flow
}