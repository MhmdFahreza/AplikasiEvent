package com.example.aplikasievent

data class Event(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val ownerName: String,
    val beginTime: String,
    val quota: Int,
    val registrants: Int,
    val description: String,
    val link: String,
    var isFavorite: Boolean = false,
    val isFinished: Boolean
)

