package com.example.xmppIntegartion.model

data class ChatMessage(
    val user: String,
    val text: String,
    val isMe: Boolean
)
