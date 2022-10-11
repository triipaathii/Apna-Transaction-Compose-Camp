package com.example.apnatransaction.modal

import java.util.Date

data class Transaction(
    val amountRes: Int,
    val titleRes: Int,
    val dateChosen: Date,
    val description: String? = null
)
