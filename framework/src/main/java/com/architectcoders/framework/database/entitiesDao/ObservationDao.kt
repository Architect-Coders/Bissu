package com.architectcoders.framework.database.entitiesDao

import androidx.room.*
import com.architectcoders.framework.database.entities.Observation

/**
 * Created by Anibal Cortez on 2019-12-11.
 */

@Dao
interface ObservationDao {


    @Query("SELECT * FROM Observation WHERE Observation.book LIKE :id")
    fun getObsevartionsByBook(id: String): List<Observation>

    @Query("SELECT * FROM Observation WHERE Observation.userId LIKE :id")
    fun getObsevartionsByUser(id: String): List<Observation>

    @Query("SELECT * FROM Observation WHERE id LIKE :id")
    fun getObsevartions(id: String): Observation?

    @Query("SELECT COUNT() FROM Observation")
    fun count(): Int

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(observation: Observation)

    @Update
    fun update(observation: Observation)

    @Query("DELETE FROM Observation")
    fun deleteAllObservations()
}