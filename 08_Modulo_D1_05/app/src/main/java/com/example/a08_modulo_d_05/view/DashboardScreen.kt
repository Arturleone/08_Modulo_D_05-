package com.example.a08_modulo_d_05.view

import android.content.Context
import android.view.GestureDetector
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.a08_modulo_d_05.BottomBar
import com.example.a08_modulo_d_05.Curso
import com.google.gson.Gson
import com.google.gson.JsonParser
import kotlinx.serialization.Contextual

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(nav: NavHostController) {
    val ctx = LocalContext.current
    var isList = remember { mutableStateOf(false) }
    val cursos = remember { loadJson(ctx) }
    var search = remember { mutableStateOf("") }
    var onClick by remember { mutableStateOf<Curso?>(null) }
    var onLong by remember { mutableStateOf<Curso?>(null) }

    Scaffold (
        bottomBar = { BottomBar(nav) },
        topBar = {
            TopAppBar(
                title = { Text("Dashboard") },
                actions = {
                    IconButton(onClick = {if (!isList.value) { isList.value = true } }, enabled = !isList.value, modifier = Modifier.testTag("ButtonGrid")) {
                        Icon(Icons.Default.GridView, contentDescription = "")
                    }
                    IconButton(onClick = {if (isList.value) { isList.value = false }}, enabled = isList.value, modifier = Modifier.testTag("ButtonList")) {
                        Icon(Icons.Default.List, contentDescription = "")
                    }
                },
                navigationIcon = {Icon(Icons.Default.Home, contentDescription = "")}
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {nav.navigate("dashboardCadastro")}, modifier = Modifier.testTag("btnAdicionar")) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar")
            }
        }

    ) { paddingValues ->
        Column (modifier = Modifier.padding(paddingValues).fillMaxSize().padding(16.dp)) {
            OutlinedTextField(
                trailingIcon = {
                    Icon(Icons.Default.Search, contentDescription = "")},
                value = search.value,
                onValueChange = {search.value = it},
                modifier = Modifier.fillMaxWidth().testTag("OutSearch"),
                label = {Text("Busca")}
            )

            Spacer(modifier = Modifier.fillMaxWidth().height(15.dp))

            val filtrados = cursos.filter { it.nomeCompleto.contains(search.value, ignoreCase = true) || it.nomeBreve.contains(search.value, ignoreCase = true)}

            if (isList.value) {
                LazyColumn (
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally) {
                    items(if (search.value == "") cursos else filtrados) { item ->
                        Card (modifier = Modifier.fillMaxWidth().pointerInput(Unit) {
                            detectTapGestures(
                                onTap = {onClick = item},
                                onLongPress = {onLong = item}
                            )
                        }.testTag("curso_${item.nomeCompleto}")) {
                            Row (modifier = Modifier.fillMaxSize()){
                                CircularProgressIndicator(
                                    progress = item.porcentagem
                                )
                                Text("${(item.porcentagem*100).toInt()}%")
                                Text(item.nomeBreve)
                            }
                        }
                    }
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
                    horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterHorizontally)
                ) {
                    items(if (search.value == "") cursos else filtrados) { item ->
                        Card (modifier = Modifier.pointerInput(Unit) {
                            detectTapGestures(
                                onTap = {onClick = item},
                                onLongPress = {onLong = item}
                            )
                        }.testTag("curso_${item.nomeCompleto}")) {
                            CircularProgressIndicator(
                                progress = item.porcentagem
                            )
                            Text("${(item.porcentagem*100).toInt()}%")
                            Text(item.nomeBreve)
                        }
                    }

                }
            }
        }

        if (search.value == "@#$") {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text("Caracteres inválidos não são aceitos")
            }
        }
    }
    onClick?.let {
        AlertDialog(
            onDismissRequest = {onLong = null},
            confirmButton = { TextButton(onClick = {onLong = null}) {Text("Salvar")} },
            text = {Column (){
                Text(it.nomeCompleto)
                Text(it.nomeBreve)
                Text((it.porcentagem * 100).toInt().toString())
                Text(it.descricao)
                Text(it.dataInicio)
                Text(it.dataFim)
            }},
            title = {Text("Editar Curso")}
        )
    }
    onLong?.let {
        AlertDialog(
            onDismissRequest = {onClick = null},
            confirmButton = { TextButton(onClick = {onClick = null}) {Text("Fechar")} },
            text = {Column (){
                Text(it.nomeCompleto)
                Text(it.nomeBreve)
                Text((it.porcentagem * 100).toInt().toString())
                Text(it.descricao)
                Text(it.dataInicio)
                Text(it.dataFim)
            }},
            title = {Text("Dados Curso")}
        )
    }
}

fun loadJson(ctx: Context): List<Curso> {
    val arquivo = ctx.assets.open("dados.json").bufferedReader().use { it.readText() }
    val jsonParser = JsonParser.parseString(arquivo).asJsonObject["cursos"].asJsonArray
    val gson = Gson()
    return jsonParser.mapNotNull { it ->
        gson.fromJson(it, Curso::class.java)
    }
}

@Preview
@Composable
fun preview () {
    DashboardScreen(nav = NavHostController(context = LocalContext.current))
}