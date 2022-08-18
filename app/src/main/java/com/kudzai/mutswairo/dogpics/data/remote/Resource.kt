package com.kudzai.mutswairo.dogpics.data.remote

enum class Status{
    SUCCESS,
    LOADING,
    ERROR
}

sealed class Resource<out T> (val status: Status, val data: T?, val message: String?) {
    data class Success<out R>(val _data: R) : Resource<R> (
        status = Status.SUCCESS,
        data = _data,
        message = null
    )
    data class Loading<out R> (val _data: R?, val _message: String?) : Resource<R> (
        status = Status.LOADING,
        data = _data,
        message = _message
    )
    data class Error(val errorMessage: String) : Resource<Nothing> (
        status = Status.ERROR,
        data = null,
        message = errorMessage
    )
}
