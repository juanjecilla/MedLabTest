package com.medlab.medlabtest.base

interface IBasePresenter<ViewT> {

    fun onViewActive(view: ViewT)

    fun onViewInactive()
}

