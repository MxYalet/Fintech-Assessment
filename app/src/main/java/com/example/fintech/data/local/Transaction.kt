package com.example.fintech.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val senderName: String,
    val receiverName: String,
    val bank: String,
    val amount: Double,
    val timestamp: Long = System.currentTimeMillis()
)
