package com.example.aplikasievent

data class Event(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val ownerName: String,
    val beginTime: String,
    val quota: Int,
    val registrant: Int,
    val sisaquota: Int,
    val description: String,
    val link: String
)
