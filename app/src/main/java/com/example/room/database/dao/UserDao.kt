package com.example.room.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.room.database.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user:User)

    @Query("SELECT * FROM user_table ORDER BY id ASC")
    fun readAllUsers():LiveData<List<User>>

    @Update
    suspend fun updateUser(user : User)

    @Delete
    suspend fun deleteUser(user : User)

    @Query("DELETE FROM user_table")
    fun deleteAll()


}