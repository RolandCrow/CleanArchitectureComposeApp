package com.example.presentation_post.single

import android.content.Context
import com.example.domain.usecase.GetPostUseCase
import com.example.presentation_common.state.CommonResultConverter
import com.example.presentation_post.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PostConverter @Inject constructor(@ApplicationContext private val context: Context):
CommonResultConverter<GetPostUseCase.Response, PostModel>(){

    override fun convertSuccess(data: GetPostUseCase.Response): PostModel {
        return PostModel(
        context.getString(R.string.title, data.post.title),
        context.getString(R.string.body, data.post.body)
        )
    }

}