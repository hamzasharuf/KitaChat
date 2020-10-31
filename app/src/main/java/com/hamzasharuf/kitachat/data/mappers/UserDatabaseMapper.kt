package com.hamzasharuf.kitachat.data.mappers

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.google.firebase.Timestamp
import com.hamzasharuf.kitachat.data.database.entities.UserEntity
import com.hamzasharuf.kitachat.data.models.User

object UserDatabaseMapper {

    fun mapFromEntity(userEntity: UserEntity) =
        with(userEntity) {
            User(
                userID,
                userName,
                userPhone,
                imageProfile,
                status,
                gender,
                imageCover ?: "",
                email ?: "",
                dateOfBirth ?: "",
                Timestamp(registeredAt),
            )
        }

    fun mapToEntity(user: User) =
        with(user) {
            UserEntity(
                userID,
                userName,
                userPhone,
                imageProfile,
                status,
                gender,
                imageCover,
                email,
                dateOfBirth,
                registeredAt.toDate(),
            )
        }

    fun mapFromEntityList(users: List<UserEntity>): List<User> {
        return if (users.isNotEmpty())
            users.map { mapFromEntity(it) }
        else
            listOf()
    }

}