package com.architectcoders.bissu.data.database

import androidx.room.*
import com.architectcoders.bissu.data.database.entities.User

/**
 * Created by Anibal Cortez on 2019-12-11.
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM User LIMIT(1)")
    fun getUser(): User

    @Query("SELECT COUNT() FROM User")
    fun userCount(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query( "SELECT * FROM User WHERE username = :username")
    fun findByUsername(username: String): User

}