package com.indogusmas.paging3android

import androidx.paging.PagingSource
import androidx.paging.PagingState
import java.lang.Exception

private  const val INITIAL_PAGE = 1

class GithubRepoPagingSource (
    private  val api: GithubApi,
    private val username: String
    ): PagingSource<Int, Repo>(){

    override fun getRefreshKey(state: PagingState<Int, Repo>): Int? {
        return  state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?:state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Repo> {
        return  try {
            val page = params.key ?: INITIAL_PAGE
            val response = api.fetchRepos(page,params.loadSize)
            LoadResult.Page(
                data = response.data,
                prevKey = if (page == INITIAL_PAGE) null else page - 1,
                nextKey = if (response.data.isEmpty()) null else page + 1
            )

        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

}