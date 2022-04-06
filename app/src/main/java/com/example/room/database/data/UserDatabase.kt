package com.example.room.database.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.room.database.dao.UserDao
import com.example.room.database.models.User

@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao():UserDao

    companion object{
        @Volatile
        private var INSTANCE:UserDatabase ?=null;

        fun getDatabase(context: Context) : UserDatabase{
            val temp = INSTANCE
            if(temp != null){
                return temp
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_databases"
                ).build()

                INSTANCE = instance
                return instance
            }
        }

    }

}