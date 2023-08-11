package com.test.core_network.data.response

import com.google.gson.annotations.SerializedName

data class SearchResult(
    val total: Int,
    @SerializedName("total_pages")
    val totalPages: Int,
    val results: List<Photo>
)