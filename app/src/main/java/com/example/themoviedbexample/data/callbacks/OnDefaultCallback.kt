package com.example.themoviedbexample.data.callbacks

interface OnDefaultCallback<Type> {
    fun onSuccess(result: Type)
    fun onError(error: String)
}

