package com.example.appservice
data class DataResponse(
    val results: List<Results>
)

data class Results(
    val id : Int,
    val name: String,
    val species: String,
    val image: String,
    val origin: Origin
)

data class Origin(
    val name: String
)