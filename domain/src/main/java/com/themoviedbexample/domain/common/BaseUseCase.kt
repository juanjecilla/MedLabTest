package com.themoviedbexample.domain.common

import io.reactivex.Observable

abstract class BaseUseCase<T>(private val transformer: Transformer<T>) {

    abstract fun createObservable(data: Map<String, String>? = null): Observable<T>

    fun observable(withData: Map<String, String>? = null): Observable<T> {
        return createObservable(withData).compose(transformer)
    }

}