package com.example.xmppIntegartion.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.xmppIntegartion.ui.routing.LocalNavigator
import com.example.xmppIntegartion.ui.routing.Screen
import com.example.xmppIntegartion.xmpp.ChatRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun UserListScreen() {
    val scope = rememberCoroutineScope()

    var users by remember { mutableStateOf<List<String>>(emptyList()) }
    var loading by remember { mutableStateOf(false) }
    var error by remember { mutableStateOf<String?>(null) }

    val navController = LocalNavigator.current


    LaunchedEffect(Unit) {
        loading = true
        scope.launch {
            try {
                val list = withContext(Dispatchers.IO) {
                    ChatRepository.getRosterUsers()
                }
                users = list
            } catch (e: Exception) {
                error = e.message
            } finally {
                loading = false
            }
        }
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {

        Text(
            text = "XMPP Users (Roster)",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(12.dp))

        if (loading) {
            CircularProgressIndicator()
            return@Column
        }

        error?.let {
            Text(text = "Error: $it", color = MaterialTheme.colorScheme.error)
            return@Column
        }

        if (users.isEmpty()) {
            Text("No users found in roster.")
            Text("Add users in OpenFire roster or using createEntry().")
            return@Column
        }

        LazyColumn {
            items(users) { user ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 6.dp)
                        .clickable {
                            navController.navigate(Screen.ChatScreen(user)) }
                ) {
                    Text(
                        text = user,
                        modifier = Modifier.padding(16.dp),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}

