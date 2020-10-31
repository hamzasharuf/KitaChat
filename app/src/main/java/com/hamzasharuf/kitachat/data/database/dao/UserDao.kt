package com.hamzasharuf.kitachat.data.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hamzasharuf.kitachat.data.database.entities.UserEntity

@Dao
abstract class UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insert(user: UserEntity)

    @Query("SELECT * FROM ${UserEntity.TABLE_NAME} LIMIT 1")
    abstract suspend fun getUser(): List<UserEntity>
}