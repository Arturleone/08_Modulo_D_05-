package com.example.a08_modulo_d_05.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.a08_modulo_d_05.BottomBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RelatorioScreen (nav: NavHostController) {
    Scaffold (bottomBar = { BottomBar(nav) },
        topBar = { TopAppBar(title = {Text("Relatórios")})  }) { paddingValues ->
        Column (modifier = Modifier.padding(paddingValues)){

        }
    }
}