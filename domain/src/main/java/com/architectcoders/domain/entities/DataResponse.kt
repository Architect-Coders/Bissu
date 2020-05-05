package com.architectcoders.domain.entities


sealed class DataResponse<out T: Any> {
    data class Success<T: Any>(val data : T): DataResponse<T>()
    object NetworkError :  DataResponse<Nothing>()
    object SessionError : DataResponse<Nothing>()
    data class ServerError(val errorCode : Int) :  DataResponse<Nothing>()
}