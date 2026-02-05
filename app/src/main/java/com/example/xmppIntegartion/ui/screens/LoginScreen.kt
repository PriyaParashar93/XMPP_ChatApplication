package com.example.xmppIntegartion.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.xmppIntegartion.ui.routing.LocalNavigator
import com.example.xmppIntegartion.ui.routing.Screen
import com.example.xmppIntegartion.xmpp.XmppManager

@Composable
fun LoginScreen() {

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }
    val navController = LocalNavigator.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {

        Text("XMPP Login", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") }
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") }
        )

        Spacer(Modifier.height(16.dp))

        Button(onClick = {
            XmppManager.connect(
                username,
                password,
                onSuccess = {navController.navigate(Screen.UserListScreen)},
                onError = { error = it }
            )
        }) {
            Text("Login")
        }

        error?.let {
            Text(it, color = Color.Red)
        }
    }
}
