package com.example.presentation_post.single

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.presentation_common.navigation.PostInput
import com.example.presentation_common.state.CommonScreen
import com.example.presentation_post.single.PostModel

@Composable
fun PostScreen(
    viewModel: PostViewModel,
    postInput: PostInput
) {
    viewModel.uiStateFlow.collectAsState().value.let { result->
        CommonScreen(result) { postModel->
            Post(postModel)
        }
        LaunchedEffect(postInput.postId) {
            viewModel.submitAction(PostUiAction.Load(postInput.postId))
        }
    }
}

@Composable
fun Post(postModel: PostModel) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = postModel.title)
        Text(text = postModel.body)
    }
}