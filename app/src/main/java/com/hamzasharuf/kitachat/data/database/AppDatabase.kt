package com.hamzasharuf.kitachat.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hamzasharuf.kitachat.data.database.converters.DateTypeConverter
import com.hamzasharuf.kitachat.data.database.dao.UserDao
import com.hamzasharuf.kitachat.data.database.entities.UserEntity

@Database(
    entities = [UserEntity::class],
    version = AppDatabase.DATABASE_VERSION,
    exportSchema = false,
)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase: RoomDatabase() {

    abstract val userDao: UserDao

    companion object {
        const val DATABASE_NAME = "kitachat.db"
        const val DATABASE_VERSION = 2
    }
}