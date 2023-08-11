package com.test.core_network.data

import com.test.core_network.data.response.SearchResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NetworkApi {

    companion object {
         private const val API_KEY = "l5hEJUno6PtmClz4dvmxeDZO5y1B9WGYoHf3IMipCZw"
    }

    @Headers("Accept-Version: v1", "Authorization: Client-ID $API_KEY")
    @GET("search/photos")
    suspend fun searchPictures(
        @Query("query") query: String,
        @Query("page") page: Int=1,
        @Query("per_page") perPage: Int = 20
    ): Response<SearchResult>

}
