package com.demox.currency.screens.main

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.base_ui.view.UnderDevelopmentText

@Composable
fun CurrencyMainScreen(navController: NavController) {
    val viewModel: CurrencyMainViewModel = hiltViewModel()
    val test by viewModel.teste.collectAsState()


    if (test.isNullOrEmpty()){
        Text("CurrencyMainScreen")
      //  UnderDevelopmentText()
    } else{
        Text(test)
    }

}
