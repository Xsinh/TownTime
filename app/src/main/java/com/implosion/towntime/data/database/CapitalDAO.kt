package com.implosion.towntime.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.implosion.towntime.data.database.entity.CapitalEntity

@Dao
interface CapitalDAO {

    @Insert
    suspend fun insert(capital: CapitalEntity)

    @Update
    suspend fun update(capital: CapitalEntity)

    @Query("SELECT * FROM capitals")
    suspend fun getAllCapitals(): List<CapitalEntity>

    @Query("SELECT * FROM capitals WHERE isLastSelected = 1 LIMIT 1")
    suspend fun getLastSelectedCapital(): CapitalEntity?

    @Query("SELECT * FROM capitals ORDER BY name LIMIT 1")
    suspend fun getFirstCapitalAlphabetically(): CapitalEntity

    @Query("SELECT * FROM capitals WHERE name = :name LIMIT 1")
    suspend fun getCapitalByName(name: String): CapitalEntity?

    @Query("UPDATE capitals SET isLastSelected = 0 WHERE isLastSelected = 1")
    suspend fun resetLastSelected()
}