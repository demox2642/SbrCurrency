package com.demox.currency

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.demox.currency.screens.main.CurrencyMainScreen

sealed class CurrencyScreens(
    val route: String
) {
    object CurrencyMain : CurrencyScreens("currency_main")
}

fun NavGraphBuilder.currencyScreens(navController: NavController) {
    composable(CurrencyScreens.CurrencyMain.route) { CurrencyMainScreen(navController) }
}
