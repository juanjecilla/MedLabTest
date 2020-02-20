package com.themoviedbexample.presentation.utils

import com.themoviedbexample.domain.common.Transformer
import io.reactivex.Observable
import io.reactivex.ObservableSource

class TestTransformer<T>: Transformer<T>() {
    override fun apply(upstream: Observable<T>): ObservableSource<T> {
        return upstream
    }
}