package com.example.apnatransaction.modal

data class Transaction(
    val amount: String="0",
    val title: String,
    val dateChosen: String,
    val description: String? = null
)
