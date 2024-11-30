package com.implosion.towntime.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.implosion.towntime.data.database.entity.CapitalEntity

@Database(entities = [CapitalEntity::class], version = 1, exportSchema = false)
abstract class CapitalDatabase : RoomDatabase() {

    abstract fun capitalDao(): CapitalDAO
}