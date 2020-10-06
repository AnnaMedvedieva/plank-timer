package org.company.annamedvedieva.myplanktimer.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PlankDao {

    @Insert
    suspend fun insert(plank: Plank)

    @Query("SELECT * from plank_time_table WHERE plankId = :id")
    suspend fun get(id: Long): Plank

    @Query("SELECT * from plank_time_table WHERE plankId = :id")
    fun getPlank(id: Long): LiveData<Plank>

    @Query("SELECT * from plank_time_table ORDER BY date DESC")
    fun getAllPlanks(): LiveData<List<Plank>>

    @Query("DELETE FROM plank_time_table WHERE plankId = :id")
    suspend fun deletePlankById(id: Long)

}