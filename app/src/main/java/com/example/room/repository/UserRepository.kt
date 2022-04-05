package com.example.room.repository

import androidx.lifecycle.LiveData
import com.example.room.database.dao.UserDao
import com.example.room.database.models.User

class UserRepository(private val userDao: UserDao) {

    suspend fun addUser(user: User){
        userDao.addUser(user)
    }

    suspend fun getAllUsers(): LiveData<List<User>>{
        return userDao.readAllUsers()
    }
}