package com.test.feature_search.utils

import com.test.feature_search.domain.Picture
import com.test.repository_search.domain.DownloadedImages

fun List<DownloadedImages>.toPictures(): List<Picture> {

    return this.map { Picture(it.images) }

}
