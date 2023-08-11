package com.test.core_network.data.response

import com.google.gson.annotations.SerializedName

data class Photo(
    val id: String,
    @SerializedName("created_at")
    val createdAt: String,
    val width: Int,
    val height: Int,
    val color: String,
    @SerializedName("blur_hash")
    val blurHash: String,
    val likes: Int,
    @SerializedName("liked_by_user")
    val likedByUser: Boolean,
    val description: String?,
    @SerializedName("current_user_collections")
    val currentUserCollections: List<Any>,
    val urls: Urls,
)


