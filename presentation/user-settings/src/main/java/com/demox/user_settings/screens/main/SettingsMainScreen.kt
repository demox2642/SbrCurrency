package com.demox.user_settings.screens.main

import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.demox.presentation.user_settings.R
import com.demox.user_settings.model.CurrencySetting
import com.example.base_ui.theme.AppTheme
import com.example.base_ui.view.dialogs.CustomAllertDialog

@Composable
fun SettingsMainScreen(navController: NavController) {
    val viewModel: SettingsMainViewModel = hiltViewModel()
    val currencySetting by viewModel.currencySetting.collectAsState()
    val settingActive by viewModel.settingState.collectAsState()
    val settingValue by viewModel.settingValue.collectAsState()

    val validateError = doubleValidator(settingValue).not()
    val warningState by viewModel.warningState.collectAsState()

    Surface(

        color = AppTheme.colors.systemBackgroundPrimary
    ) {
        if (currencySetting.equals(CurrencySetting(active = settingActive, value = settingValue.toDoubleOrNull())).not()) {
            BackPressHandler() {
                viewModel.changeWarningState()
            }
        }

        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(
                    text = stringResource(id = R.string.title),
                    style = AppTheme.typography.h1,
                    color = AppTheme.colors.systemTextPrimary
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

            ) {
                Text(
                    text = stringResource(id = R.string.currency_observer),
                    style = AppTheme.typography.buttonM,
                    color = AppTheme.colors.systemTextPrimary
                )
                Column() {
                    Switch(
                        checked = settingActive,
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = AppTheme.colors.colorGraphIndigo
                        ),
                        onCheckedChange = { viewModel.changeSettingState() }
                    )
                }
            }
            if (settingActive) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.enter_message),
                        style = AppTheme.typography.buttonM,
                        color = AppTheme.colors.systemTextPrimary
                    )
                    Card(
                        shape = RoundedCornerShape(10.dp),
                        backgroundColor = if (validateError) {
                            AppTheme.colors.colorBackgroundAlert
                        } else { AppTheme.colors.systemBackgroundTertiary }
                    ) {
                        TextField(
                            value = settingValue,
                            isError = validateError,
                            modifier = Modifier.size(width = 150.dp, height = 50.dp),
                            onValueChange = { viewModel.changeValue(it)  },
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }
                }
            }
            if (currencySetting.equals(CurrencySetting(active = settingActive, value = settingValue.toDoubleOrNull())).not()) {
                Row(modifier = Modifier.fillMaxWidth().padding(top = 50.dp)) {
                    Button(
                        onClick = {
                            viewModel.saveSetting(settingActive, settingValue.toDouble())
                            navController.navigate("currency_main")
                        },
                        modifier = Modifier.fillMaxWidth(),
                        enabled = (validateError.not() )
                    ) {
                        Text(text = stringResource(id = R.string.save))
                    }
                }
            }
        }

        if (warningState) {
            CustomAllertDialog(
                title = stringResource(id = R.string.setting_allert_title),
                message = stringResource(id = R.string.setting_allert),
                confermButtonText = stringResource(id = R.string.confirm),
                dismissButtonText = stringResource(id = R.string.esc),
                closeDialog = { viewModel.changeWarningState() },
                changePermissionState = { navController.navigate("currency_main") }
            )
        }
    }
}

fun doubleValidator(string: String): Boolean {
    val maybeDouble = string.toDoubleOrNull()
    return if (string == "") {
        true
    } else {
        maybeDouble != null
    }
}

@Composable
fun BackPressHandler(
    backPressedDispatcher: OnBackPressedDispatcher? =
        LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher,
    onBackPressed: () -> Unit
) {
    val currentOnBackPressed by rememberUpdatedState(newValue = onBackPressed)

    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                currentOnBackPressed()
            }
        }
    }

    DisposableEffect(key1 = backPressedDispatcher) {
        backPressedDispatcher?.addCallback(backCallback)

        onDispose {
            backCallback.remove()
        }
    }
}
