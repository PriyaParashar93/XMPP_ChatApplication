package com.example.xmppIntegartion.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.xmppIntegartion.model.ChatMessage
import com.example.xmppIntegartion.ui.components.MessageBubble
import com.example.xmppIntegartion.xmpp.ChatRepository

@Composable
fun ChatScreen(
     user: String) {

    val messages = remember { mutableStateListOf<ChatMessage>() }
    var text by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        ChatRepository.listen {
            messages.add(it)
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(messages) { msg ->
                MessageBubble(msg)
            }
        }

        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.weight(1f)
            )
            Button(onClick = {
                Log.i("MSG sent to :",user)
                ChatRepository.sendMessage(user, text)
                messages.add(ChatMessage("me", text, true))
                text = ""
            }) {
                Text("Send")
            }
        }
    }
}
