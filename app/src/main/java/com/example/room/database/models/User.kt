package com.example.room.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = false)
    val id:Int,
    val firstName:String,
    val lastName:String,
    val age :Int
    ) {
}