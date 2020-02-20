package com.themoviedbexample.domain.common.flowable

import io.reactivex.Flowable

abstract class BaseFlowableUseCase<T>(private val transformer: FlowableRxTransformer<T>) {

    abstract fun createFlowable(data: Map<String, Any>? = null): Flowable<T>

    fun single(withData: Map<String, Any>? = null): Flowable<T> {
        val a = createFlowable(withData)
        return a.compose(transformer)
    }
}
