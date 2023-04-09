package com.example.varorussell.network

import com.example.varorussell.network.NetworkResult.Success
import com.example.varorussell.network.NetworkResult.Error
import retrofit2.Response

sealed class NetworkResult<out R> {
    data class Success<T>(val data: T): NetworkResult<T>()
    data class Error<T>(val exception: Exception): NetworkResult<T>()


    override fun toString(): String  = when(this) {
        is Success<*> -> "Success: [data:$data]"
        is Error -> "Success: [data:$exception]"
    }
}

inline fun <T> runNetworkCatching(block: () -> T): NetworkResult<T> = try {
    Success(block())
} catch (e: Exception) {
    Error(e)
}


