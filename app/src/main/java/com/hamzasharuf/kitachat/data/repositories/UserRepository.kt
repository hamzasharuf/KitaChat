package com.hamzasharuf.kitachat.data.repositories

import androidx.lifecycle.LiveData
import com.hamzasharuf.kitachat.data.api.FirestoreApi
import com.hamzasharuf.kitachat.data.api.UserApi
import com.hamzasharuf.kitachat.data.api.responses.UpdateUserProfileResponse
import com.hamzasharuf.kitachat.data.database.dao.UserDao
import com.hamzasharuf.kitachat.data.mappers.UserDatabaseMapper
import com.hamzasharuf.kitachat.data.models.User
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class UserRepository @Inject constructor(
    private val firestoreApi: FirestoreApi,
    private val userApi: UserApi,
    private val userDao: UserDao
) {
    fun updateUserProfile(user: User):LiveData<UpdateUserProfileResponse>{
        return firestoreApi.updateUserProfile(user)
    }

    fun getCurrentUserApi() = userApi.getCurrentUser()

    fun getUserInfoApi(uid: String) = firestoreApi.getUserInfo(uid)

    suspend fun getUserCache() = UserDatabaseMapper.mapFromEntityList(userDao.getUser())

    suspend fun insertUserCache(user: User) = userDao.insert(UserDatabaseMapper.mapToEntity(user))

}