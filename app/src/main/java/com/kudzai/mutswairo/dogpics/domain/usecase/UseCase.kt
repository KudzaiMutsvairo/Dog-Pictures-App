package com.kudzai.mutswairo.dogpics.domain.usecase

import kotlinx.coroutines.flow.Flow

abstract class UseCase<out T, in R> {
    abstract suspend operator fun invoke(params: R? = null): Flow<T>
}