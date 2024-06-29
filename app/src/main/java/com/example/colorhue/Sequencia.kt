package com.example.colorhue

// Classe que determina para cada cor gerada que ela é o valor correto e quais as possíveis cores daquela dificuldade.
data class Sequencia(var corCorreta: String, val coresPossiveis: List<String>)