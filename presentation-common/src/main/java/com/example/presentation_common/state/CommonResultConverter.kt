package com.example.presentation_common.state

import com.example.domain.entity.Result
import com.example.presentation_common.UiState

abstract class CommonResultConverter<T:Any,R:Any> {

    fun convert(result: com.example.domain.entity.Result<T>): UiState<R> {
        return when(result) {
            is Result.Error -> {
                UiState.Error(result.exception.localizedMessage.orEmpty())
            }
            is Result.Success -> {
                UiState.Success(convertSuccess(result.data))
            }
        }
    }

    abstract fun convertSuccess(data: T): R
}