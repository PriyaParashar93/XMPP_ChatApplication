package com.example.xmppIntegartion.ui.routing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.xmppIntegartion.ui.screens.ChatScreen
import com.example.xmppIntegartion.ui.screens.LoginScreen
import com.example.xmppIntegartion.ui.screens.UserListScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    CompositionLocalProvider(LocalNavigator provides navController) {

        NavHost(
            navController = navController,
            startDestination = Screen.LoginScreen
        ) {

            composable<Screen.LoginScreen> {
                LoginScreen()
            }

            composable<Screen.ChatScreen> { backStackEntry ->
                val chatScreen: Screen.ChatScreen = backStackEntry.toRoute()
                ChatScreen(chatScreen.userId)
            }
            composable<Screen.UserListScreen> {
                UserListScreen()
            }
        }
    }
}
