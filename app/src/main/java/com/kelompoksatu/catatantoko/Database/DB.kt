package com.kelompoksatu.catatantoko.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.kelompoksatu.catatantoko.Entity.UserEntity

@Database(entities = [UserEntity::class], version = 1)
abstract class DB : RoomDatabase() {
    abstract fun userDao() : UserDAO

    companion object {
        @Volatile
        private var INSTANCE: DB? = null

        fun getInstance(context: Context): DB {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    DB::class.java,
                    "account_database"
                ).fallbackToDestructiveMigration().build()
                    .also { INSTANCE = it }
            }
        }
    }
}