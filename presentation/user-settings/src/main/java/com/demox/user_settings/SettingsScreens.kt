package com.demox.user_settings

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.demox.user_settings.screens.main.SettingsMainScreen

sealed class SettingsScreens(
    val route: String
) {
    object SettingsMain : SettingsScreens("setting_main")
}

fun NavGraphBuilder.settingsScreens(navController: NavController) {
    composable(SettingsScreens.SettingsMain.route) { SettingsMainScreen(navController) }
}
