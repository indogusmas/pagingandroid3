package com.indogusmas.paging3android

import com.google.gson.annotations.SerializedName

data class BaseResponse (
    @SerializedName("data")
    val data: List<Repo>
    )