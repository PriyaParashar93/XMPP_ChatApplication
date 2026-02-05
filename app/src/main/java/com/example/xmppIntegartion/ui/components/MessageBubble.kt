package com.example.xmppIntegartion.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.xmppIntegartion.model.ChatMessage

@Composable
fun MessageBubble(msg: ChatMessage) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = if (msg.isMe) Arrangement.End else Arrangement.Start
    ) {
        Surface(
            color = if (msg.isMe) Color(0xFFDCF8C6) else Color.LightGray,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(4.dp)
        ) {
            Text(msg.text, modifier = Modifier.padding(8.dp))
        }
    }
}
