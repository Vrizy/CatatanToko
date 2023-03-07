package com.kelompoksatu.catatantoko.Database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.kelompoksatu.catatantoko.Entity.UserEntity

@Dao
interface UserDAO {

    @Insert
    fun register(user: UserEntity) : Long

    @Query("SELECT * FROM users")
    fun getAll(): LiveData<List<UserEntity>>

    @Query("DELETE FROM users")
    fun deleteUser()

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    fun getUser(username: String, password: String): UserEntity?

    @Query("SELECT * FROM users WHERE username = :username")
    fun checkUsername(username: String): UserEntity?
}