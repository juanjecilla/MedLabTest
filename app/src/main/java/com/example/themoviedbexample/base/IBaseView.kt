package com.example.themoviedbexample.base

interface IBaseView {

//    val mContext: Context

    fun showToastMessage(message: String)

    fun setProgressBar(show: Boolean)
}
