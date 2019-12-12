package com.architeccoders.bissu.data.database

import androidx.room.*

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
@Dao
interface UserDao {

    @Query("SELECT * FROM User WHERE username = :username AND password=:password")
    fun findById(username: String, password : String): User

    @Query("SELECT COUNT() FROM User")
    fun userCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

}