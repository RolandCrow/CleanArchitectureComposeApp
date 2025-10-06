package com.example.presentation_user

import com.example.presentation_common.state.UiAction

sealed class UserUiAction: UiAction {
    data class Load(val userId: Long): UserUiAction()
}