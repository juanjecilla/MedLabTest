package com.themoviedbexample.domain.common.flowable

import io.reactivex.FlowableTransformer

abstract class FlowableRxTransformer<T> : FlowableTransformer<T, T>

