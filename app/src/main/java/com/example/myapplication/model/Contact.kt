package com.example.myapplication.model

data class Contact(
    val rawContactId: Int,
    val name: String,
    val starred: Boolean,
    val hasPhoneNumber: Boolean
)