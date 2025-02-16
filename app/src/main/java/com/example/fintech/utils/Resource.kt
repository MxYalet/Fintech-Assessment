package com.example.fintech.utils


sealed class Resource<T> {


    class Success<T>(data: T) : Resource<T>()
    class Error<T>(errorCode: Int, data: T? = null) : Resource<Nothing>()
    class Loading<T>() : Resource<T>()

}
