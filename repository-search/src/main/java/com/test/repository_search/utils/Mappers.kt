package com.test.repository_search.utils

import com.test.core_network.data.response.SearchResult
import com.test.repository_search.domain.DownloadedImages

fun SearchResult.toDownloadedImages(): List<DownloadedImages> {

    return this.results.map { DownloadedImages(it.urls.thumb) }

}

