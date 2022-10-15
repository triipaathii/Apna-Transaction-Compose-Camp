package com.example.apnatransaction.modal

import androidx.compose.runtime.MutableState

data class Transaction(
    val amount: String="0",
    val title: String,
    val dateChosen: MutableState<String>,
    val description: String? = null
)
