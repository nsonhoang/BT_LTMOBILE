package com.example.bai1_tuan5

data class Course(
    val detailCourse: String = "",
    val nameCosurse: String = "",
    var aloList: List<Alo> = emptyList()
)

data class Alo(
    val detailExercise: String= "",
    val image: String="",
    val level: String="",
    val titleExercise: String="",
    val totalTime: String=""

)