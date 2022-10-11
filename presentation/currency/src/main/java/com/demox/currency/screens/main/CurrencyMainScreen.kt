package com.demox.currency.screens.main

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.demox.currency.model.Curency
import com.demox.presentation.currency.R
import com.example.base_ui.theme.AppTheme

@Composable
fun CurrencyMainScreen(navController: NavController) {
    val viewModel: CurrencyMainViewModel = hiltViewModel()
    val currencyList by viewModel.currencyList.collectAsState()
    val loadState by viewModel.loadState.collectAsState()

    Scaffold(
        topBar = {
            TopBar() {
                navController.navigate("setting_main")
            }
        },
        backgroundColor = AppTheme.colors.systemBackgroundPrimary
    ) {
        if (loadState) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(100.dp),

                    color = AppTheme.colors.colorGraphIndigo,
                    strokeWidth = 10.dp
                )
            }
        } else {
            LazyColumn() {
                items(currencyList) { CurrencyListItem(it) }
            }
        }
    }
}

@Composable
fun TopBar(goToSetting: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth()) {
        ConstraintLayout {
            val (backButton, text) = createRefs()

            IconButton(
                onClick = goToSetting,
                modifier = Modifier.constrainAs(backButton) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }

            ) {
                Icon(
                    painterResource(id = R.drawable.setting),
                    contentDescription = stringResource(id = R.string.setting),
                    tint = AppTheme.colors.systemGraphPrimary,
                    modifier = Modifier.size(25.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(text) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    },
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(id = R.string.top_bar),
                    textAlign = TextAlign.Center,
                    style = AppTheme.typography.h3,
                    color = AppTheme.colors.systemTextPrimary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTopBar() {
    TopBar() {}
}

@Composable
fun CurrencyListItem(currencyItem: Curency) {
    val beckgroundColor = if (currencyItem.highlight == true) {
        AppTheme.colors.colorBackgroundWarning
    } else {
        AppTheme.colors.systemBackgroundTertiary
    }

    val textColor = if (currencyItem.highlight == true) {
        AppTheme.colors.colorGraphWarning
    } else {
        AppTheme.colors.systemTextPrimary
    }

    Card(
        modifier = Modifier.fillMaxWidth()
            .padding(
                start = 10.dp,
                end = 10.dp,
                top = 5.dp,
                bottom = 5.dp
            ),
        elevation = 4.dp,
        shape = RoundedCornerShape(12.dp),
        backgroundColor = beckgroundColor
    ) {
        ConstraintLayout {
            val (icon, date, value) = createRefs()

            Image(
                modifier = Modifier.size(100.dp)
                    .constrainAs(icon) {
                        start.linkTo(parent.start, margin = 5.dp)
                        top.linkTo(parent.top, margin = 5.dp)
                        bottom.linkTo(parent.bottom, margin = 5.dp)
                    },
                painter = painterResource(R.drawable.usd),
                contentDescription = stringResource(id = R.string.currency_logo)
            )

            Text(
                text = currencyItem.data,
                style = AppTheme.typography.body0,
                color = AppTheme.colors.systemTextPrimary,
                modifier = Modifier.constrainAs(date) {
                    top.linkTo(parent.top, margin = 5.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
            )

            Text(
                text = currencyItem.value.toString(),
                style = AppTheme.typography.h3,
                color = textColor,
                modifier = Modifier.constrainAs(value) {
                    top.linkTo(parent.top, margin = 10.dp)
                    bottom.linkTo(parent.bottom, margin = 10.dp)
                    end.linkTo(parent.end, margin = 10.dp)
                }
            )
        }
    }
}
