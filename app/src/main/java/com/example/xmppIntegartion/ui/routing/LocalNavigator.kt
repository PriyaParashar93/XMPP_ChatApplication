package com.example.xmppIntegartion.ui.routing


import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController

val LocalNavigator = staticCompositionLocalOf<NavHostController> {
    error("Navigator not provided")
}