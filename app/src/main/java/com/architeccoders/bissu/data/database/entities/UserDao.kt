package com.architeccoders.bissu.data.database.entities

import androidx.room.*
import com.architeccoders.bissu.data.database.entities.User

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

    @Query("DELETE  FROM User")
    fun deleteUser()
}