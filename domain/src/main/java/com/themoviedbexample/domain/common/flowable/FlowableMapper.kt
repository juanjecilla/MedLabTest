package com.themoviedbexample.domain.common.flowable

import io.reactivex.Flowable

abstract class FlowableMapper<in E, T> {

    abstract fun mapFrom(from: E): T

    fun flowable(from: E): Flowable<T> = Flowable.fromCallable { mapFrom(from) }

    fun flowable(from: List<E>): Flowable<List<T>> =
        Flowable.fromCallable { from.map { mapFrom(it) } }
}