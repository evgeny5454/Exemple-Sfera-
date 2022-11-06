package com.evgeny5454.exemplesfera.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "repositories")
data class UserTwo(
    @PrimaryKey(autoGenerate = false)
    val id: UUID,
    val fullName: String,
    val isSubscribe: Boolean,
    val subscribeMe: Boolean,
    val photoUrl: String
)
