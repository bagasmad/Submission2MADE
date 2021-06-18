package com.example.submission2made.core.data.source.remote.network

import com.example.submission2made.core.data.source.remote.StatusResponse

sealed class ApiResponse<out R> {
    data class Success<out T>(
        val data: T,
        val statusResponse: StatusResponse = StatusResponse.SUCCESS,
    ) : ApiResponse<T>()

    data class Empty(
        val message: String,
        val statusResponse: StatusResponse = StatusResponse.EMPTY,
    ) : ApiResponse<Nothing>()

    data class Error(
        val errorMessage: String,
        val statusResponse: StatusResponse = StatusResponse.ERROR,
    ) : ApiResponse<Nothing>()
}