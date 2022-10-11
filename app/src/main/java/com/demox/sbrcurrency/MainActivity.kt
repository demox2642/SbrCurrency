package com.demox.sbrcurrency

import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.demox.currency.CurrencyScreens
import com.demox.currency.currencyScreens
import com.demox.sbrcurrency.di.ConnectionManager
import com.demox.user_settings.settingsScreens
import com.example.base_ui.theme.AppTheme
import com.example.base_ui.theme.appDarkColors
import com.example.base_ui.theme.appLightColors
import com.google.accompanist.insets.ProvideWindowInsets
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var connectionManager: ConnectionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProvideWindowInsets {
                this.window.statusBarColor = ContextCompat.getColor(this, com.demox.sbrcurrency.R.color.white)
                val darkTheme: Boolean = isSystemInDarkTheme()
                val colors = if (darkTheme) appDarkColors() else appLightColors()

                val snackbarHostState = remember { mutableStateOf(SnackbarHostState()) }
                connectionManager.connectionLiveData.observe(this) {
                    if (!it) {
                        lifecycleScope.launch {
                            snackbarHostState.value.showSnackbar(
                                message = "No Network Connection",
                                duration = SnackbarDuration.Short
                            )
                        }
                    }
                }

                AppTheme.AppTheme(colors = colors) {
                    SystemUi(windows = window)
                    Surface() {
                        MainContent()

                        SnackbarHost(
                            hostState = snackbarHostState.value
                        ) {
                            Snackbar(
                                modifier = Modifier.padding(12.dp),
                                backgroundColor = AppTheme.colors.colorBackgroundAlert,
                                snackbarData = it,
                                contentColor = AppTheme.colors.colorTextAlert
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SystemUi(windows: Window) =
    AppTheme.AppTheme {
        windows.statusBarColor = AppTheme.colors.systemBackgroundPrimary.toArgb()
        windows.navigationBarColor = AppTheme.colors.systemBackgroundPrimary.toArgb()

        @Suppress("DEPRECATION")
        if (AppTheme.colors.systemBackgroundPrimary.luminance() > 0.5f) {
            windows.decorView.systemUiVisibility = windows.decorView.systemUiVisibility or
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }

        @Suppress("DEPRECATION")
        if (AppTheme.colors.systemBackgroundPrimary.luminance() > 0.5f) {
            windows.decorView.systemUiVisibility = windows.decorView.systemUiVisibility or
                View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }
    }

@Composable
fun MainContent() {
    val navController = rememberNavController()

    Scaffold(
        backgroundColor = AppTheme.colors.systemBackgroundPrimary
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = CurrencyScreens.CurrencyMain.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            currencyScreens(navController)
            settingsScreens(navController)
        }
    }
}
