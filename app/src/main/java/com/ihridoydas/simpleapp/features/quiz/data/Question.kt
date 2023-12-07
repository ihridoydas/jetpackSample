package com.ihridoydas.simpleapp.features.quiz.data

data class Question(
    val statement: String,
    val response: String,
    val propositions: List<String>
)