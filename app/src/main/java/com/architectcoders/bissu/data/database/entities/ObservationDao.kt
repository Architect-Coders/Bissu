package com.architectcoders.bissu.data.database.entities

import androidx.room.*

/**
 * Created by Anibal Cortez on 2019-12-11.
 */

@Dao
interface ObservationDao {


    @Query("SELECT * FROM Observation WHERE Observation.book_id LIKE :id")
    fun getObsevartionsByBook(id: String): List<Observation>

    @Query("SELECT * FROM Observation WHERE id LIKE :id")
    fun getObsevartions(id: String): Observation?

    @Query("SELECT COUNT() FROM Observation")
    fun count(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(observation: Observation)

    @Update
    fun update(observation: Observation)

}