package com.test.core_network

import retrofit2.Response

fun <T> Response<T>.toResult(): Result<T>{
    if (code() in 200..299){
        return Result.success(body()!!)
    }
    if (code() in 400..499){
        return Result.failure<T>(Throwable("400"))
    }
    if (code() in 500..599){
        return Result.failure<T>(Throwable("500"))
    }
    return Result.failure(Throwable(code().toString() + message()))
}