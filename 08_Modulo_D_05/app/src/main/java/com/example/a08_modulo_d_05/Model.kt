package com.example.a08_modulo_d_05

data class Curso(
    val id: Int,
    val nomeCompleto: String,
    val nomeBreve: String,
    val categoria_id: Int,
    val visivel: Boolean,
    val dataInicio: String,
    val dataFim: String,
    val descricao: String,
    val formato: String,
    val professores_id: List<Int>,
    val porcentagem: Float,
)

data class Categoria(
    val id: Int,
    val nome: String,
)

data class Professor(
    val id: Int,
    val nome: String,
    val email: String,
    val telefone: String,
    val descricao: String,
)
