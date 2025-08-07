package com.example.a08_modulo_d_05

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.GridOn
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.TableRows
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBar(
    nav: NavHostController,
    )
{
    val telaAtual = nav.currentBackStackEntryAsState().value?.destination?.route
    NavigationBar {
        NavigationBarItem(
            icon = { Icon(Icons.Default.Dashboard, contentDescription = "") },
            label = { Text("DashBoard") },
            onClick = { nav.navigate("dashboard") },
            selected = telaAtual == "dashboard",
            enabled = telaAtual != "dashboard",
            modifier = Modifier.testTag("BtnProf")
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.People, contentDescription = "") },
            label = { Text("Professores") },
            onClick = { nav.navigate("professor") },
            selected = telaAtual == "prof",
            enabled = telaAtual != "prof"
        )
        NavigationBarItem(
            icon = { Icon(Icons.Default.TableRows, contentDescription = "") },
            label = { Text("Relatórios") },
            onClick = { nav.navigate("relatorio") },
            selected = telaAtual == "relatorio",
            enabled = telaAtual != "relatorio"
        )

    }
}
