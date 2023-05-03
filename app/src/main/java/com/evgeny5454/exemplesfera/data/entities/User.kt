package com.evgeny5454.exemplesfera.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = false)
    val id: UUID,
    val fullName: String,
    val isSubscribe: Boolean,
    val subscribeMe: Boolean,
    val photoUrl: String
)
