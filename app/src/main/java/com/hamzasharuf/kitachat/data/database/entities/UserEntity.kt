package com.hamzasharuf.kitachat.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = UserEntity.TABLE_NAME)
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "user_id")
    val userID: String,

    @ColumnInfo(name = "user_name")
    val userName: String,

    @ColumnInfo(name = "user_phone")
    val userPhone: String,

    @ColumnInfo(name = "image_profile")
    val imageProfile: String,

    @ColumnInfo(name = "status")
    val status: String,

    @ColumnInfo(name = "gender")
    val gender: String,

    @ColumnInfo(name = "image_cover")
    val imageCover: String?,

    @ColumnInfo(name = "email")
    val email: String?,

    @ColumnInfo(name = "date_of_birth")
    val dateOfBirth: String?,

    @ColumnInfo(name = "registered_at")
    val registeredAt: Date,
){


    companion object{
        const val TABLE_NAME = "user_table"
    }
}