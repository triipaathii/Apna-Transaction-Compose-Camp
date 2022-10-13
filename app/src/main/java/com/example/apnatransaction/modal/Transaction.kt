package com.example.apnatransaction.modal

import java.util.*

data class Transaction(
    val amount: String,
    val title: String,
    val dateChosen: Date,
    val description: String? = null
)
