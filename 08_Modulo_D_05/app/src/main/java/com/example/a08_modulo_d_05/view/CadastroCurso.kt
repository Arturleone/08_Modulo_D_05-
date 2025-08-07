package com.example.a08_modulo_d_05.view

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Switch
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.a08_modulo_d_05.BottomBar
import com.example.a08_modulo_d_05.Categoria
import com.example.a08_modulo_d_05.Curso
import com.example.a08_modulo_d_05.Professor
import com.example.a08_modulo_d_05.R
import com.google.gson.Gson
import com.google.gson.JsonParser

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CadastroCurso(nav: NavHostController) {

    val ctx = LocalContext.current
    val nomeCompleto = remember { mutableStateOf("") }
    val nomeBreve = remember { mutableStateOf("") }
    val dataInicio = remember { mutableStateOf("") }
    val dataFim = remember { mutableStateOf("") }
    val sumario = remember { mutableStateOf("") }
    var categoria = remember { mutableStateOf("") }
    var formato = remember { mutableStateOf("") }
    var showModalProf by remember { mutableStateOf(false) }
    var listProf = remember { mutableStateOf<List<Professor>?>(emptyList()) }
    val professores = remember { loadJson3(ctx) }
    val selectedProf = remember { mutableStateListOf<String>() }
    val selectedProfString = remember { mutableStateListOf<String>() }

    val isDropDownExpanded = remember {
        mutableStateOf(false)
    }

    val isDropDownExpandedFormato = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        professores.forEach {
            selectedProfString.add(it.nome)
        }
    }
    val usernames = remember { loadJson2(ctx) }
    var visivel = remember { mutableStateOf(false) }
    val Toast: (String) -> Unit = {
        Toast.makeText(ctx, it, Toast.LENGTH_LONG).show()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cursos - Novo") },
                navigationIcon = {
                    IconButton(onClick = { nav.popBackStack() }) {
                        Icon(Icons.Default.Home, contentDescription = "")
                    }
                }
            )
        },
        bottomBar = { NavigationBar {
            NavigationBarItem(
               icon = {Icon(Icons.Default.Save, contentDescription = "")},
                selected = false,
                enabled = true,
                modifier = Modifier.testTag("BarSalvar"),
                onClick = { if (nomeCompleto.value.isBlank()) {
                        Toast("Nome completo é obrigatório")
                    return@NavigationBarItem
                    }
                    if (nomeBreve.value.isBlank()) {
                        Toast("Nome breve é obrigatório")
                        return@NavigationBarItem
                    }
                    if (categoria.value == "") {
                        Toast("Categoria é obrigatório")
                        return@NavigationBarItem
                    }
                    if (dataInicio.value.isBlank()) {
                        Toast("Data início é obrigatória")
                        return@NavigationBarItem
                    }
                    if (dataInicio.value.isBlank()) {
                        Toast("Data início é obrigatória")
                        return@NavigationBarItem
                    }

                    if (selectedProf.isEmpty()) {
                        Toast("Selecione ao menos um professor")
                        return@NavigationBarItem
                    }
                    Toast("Curso salvo com sucesso!")
                    nav.navigate("dashboard")
                },
                label = {Text("Salvar")}
            )
            NavigationBarItem(
               icon = {Icon(Icons.Default.Close, contentDescription = "")},
                selected = false,
                enabled = true,
                onClick = {nav.navigate("dashboard")},
                label = {Text("Fechar")}
            )
        } }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(
                    rememberScrollState()
                ),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(
                8.dp,
                Alignment.CenterVertically
            )
        ) {
            OutlinedTextField(
                value = nomeCompleto.value,
                onValueChange = {
                    if (it.length <= 50) {
                        nomeCompleto.value = it
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("OutNome"),
                label = { Text("Nome Completo") }
            )

            OutlinedTextField(
                value = nomeBreve.value,
                onValueChange = { if (it.length <= 15) nomeBreve.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("OutBreve"),
                label = { Text("Nome Breve") }
            )

            Box {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable {
                            isDropDownExpanded.value = true
                        }
                        .testTag("boxCategoria")
                ) {
                    Text(text = if (categoria.value == "") "Categoria" else categoria.value)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = ""
                    )
                }
                DropdownMenu(
                    expanded = isDropDownExpanded.value,
                    onDismissRequest = {
                        isDropDownExpanded.value = false
                    }) {
                    usernames.forEachIndexed { i, username ->
                        DropdownMenuItem(
                            text = {
                                Text(text = username.nome)
                            },
                            onClick = {
                                categoria.value = username.nome
                                isDropDownExpanded.value = false
                            })
                    }
                }
            }

            Row() {
                Text("Visível")
                Switch(
                    checked = visivel.value,
                    onCheckedChange = { visivel.value = it },
                )
            }

            OutlinedTextField(
                value = dataInicio.value,
                onValueChange = { dataInicio.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("OutInicio"),
                label = { Text("Data início") }
            )

            OutlinedTextField(
                value = dataFim.value,
                onValueChange = { dataFim.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("OutFim"),
                label = { Text("Data fim") }
            )

            OutlinedTextField(
                value = sumario.value,
                onValueChange = { if (it.length <= 200) sumario.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag(""),
                label = { Text("Sumário do Curso") }
            )

            Box {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable {
                            isDropDownExpandedFormato.value = true
                        }
                        .testTag("boxFormato")
                ) {
                    Text(text = if (formato.value == "") "Formato" else formato.value)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = ""
                    )
                }
                DropdownMenu(
                    expanded = isDropDownExpandedFormato.value,
                    onDismissRequest = {
                        isDropDownExpanded.value = false
                    }) {
                    DropdownMenuItem(
                        text = {
                            Text(text = "Atividade Única")
                        },
                        onClick = {
                            formato.value = "Atividade Única"
                            isDropDownExpandedFormato.value = false
                        })
                    DropdownMenuItem(
                        text = {
                            Text(text = "Formato Social")
                        },
                        onClick = {
                            formato.value = "Formato Social"
                            isDropDownExpandedFormato.value = false
                        })
                }

            }

            Row() {
                Text("Professores")
                IconButton(onClick = { showModalProf = true }, modifier = Modifier.testTag("ButAdd")) {
                    Icon(
                        Icons.Default.AddCircle,
                        contentDescription = ""
                    )
                }
            }

            selectedProf.forEach {
                val isSelected = selectedProf.contains(it)
                Row(modifier = Modifier.clickable{
                    if (isSelected) {
                        selectedProf.remove(it)
                    } else if (selectedProf.size < 5) {
                        selectedProf.add(it)
                    } else {
                        Toast("Número máximo de professores (5) atingido.")
                    }
                }) {
                    Text(it)
                    Spacer(modifier = Modifier.width(10.dp))
                    Icon(
                        imageVector = if (isSelected) Icons.Default.RemoveCircle else Icons.Default.AddCircle,
                        contentDescription = null
                    )
                }
            }

        }
    }

    if (showModalProf == true) {
        AlertDialog(
            title = { Text("Professores") },
            onDismissRequest = { showModalProf = false },
            confirmButton = {
                TextButton(onClick = { showModalProf = false }) {
                    Text("Close")
                }
            },
            text = {
                Column (
                    modifier = Modifier.padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp, Alignment.CenterVertically)
                ) {
                    selectedProfString.forEach {
                        val isSelected = selectedProf.contains(it)
                        Row(modifier = Modifier.clickable{
                            if (isSelected) {
                                selectedProf.remove(it)
                            } else if (selectedProf.size < 5) {
                                selectedProf.add(it)
                            } else {
                                Toast("Número máximo de professores (5) atingido.")
                            }
                        }) {
                            Text(it)
                            Spacer(modifier = Modifier.width(10.dp))
                            Icon(
                                imageVector = if (isSelected) Icons.Default.RemoveCircle else Icons.Default.AddCircle,
                                contentDescription = null
                            )
                        }
                    }
                }
            }
        )
    }
}

fun loadJson2(ctx: Context): List<Categoria> {
    val arquivo = ctx.assets.open("dados.json").bufferedReader().use { it.readText() }
    val jsonParser = JsonParser.parseString(arquivo).asJsonObject["categorias"].asJsonArray
    val gson = Gson()
    return jsonParser.mapNotNull { it ->
        gson.fromJson(it, Categoria::class.java)
    }
}

fun loadJson3(ctx: Context): List<Professor> {
    val arquivo = ctx.assets.open("dados.json").bufferedReader().use { it.readText() }
    val jsonParser = JsonParser.parseString(arquivo).asJsonObject["professores_id"].asJsonArray
    val gson = Gson()
    return jsonParser.mapNotNull { it ->
        gson.fromJson(it, Professor::class.java)
    }
}

