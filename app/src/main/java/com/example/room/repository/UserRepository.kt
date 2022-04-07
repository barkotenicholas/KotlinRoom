package com.example.room.repository

import androidx.lifecycle.LiveData
import com.example.room.database.dao.UserDao
import com.example.room.database.models.User

class UserRepository(private val userDao: UserDao) {

    val readAllData : LiveData<List<User>> = userDao.readAllUsers()

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun updateUser(user:User){
        userDao.updateUser(user)
    }

}