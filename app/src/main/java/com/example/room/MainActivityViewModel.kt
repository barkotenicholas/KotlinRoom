package com.example.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.room.database.data.UserDatabase
import com.example.room.database.models.User
import com.example.room.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivityViewModel (application: Application): AndroidViewModel(application) {

    private val repo : UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repo = UserRepository(userDao)
    }


    fun addUser(user:User){
        viewModelScope.launch(Dispatchers.IO) {
            repo.addUser(user)
        }
    }

}