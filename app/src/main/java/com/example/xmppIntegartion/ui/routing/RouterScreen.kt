package com.example.xmppIntegartion.ui.routing

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen() {
    @Serializable
    object LoginScreen : Screen()
    @Serializable
    data class ChatScreen(val userId: String) : Screen()
    @Serializable
    object UserListScreen : Screen()
}
