package com.implosion.towntime.data.repository

import android.content.Context
import com.implosion.towntime.data.database.CapitalDatabase
import com.implosion.towntime.data.database.entity.toDomain
import com.implosion.towntime.data.source.capitals
import com.implosion.towntime.domain.CapitalModel
import com.implosion.towntime.domain.repository.CapitalRepository
import com.implosion.towntime.domain.toDTO

class CapitalRepositoryImpl(
    val database: CapitalDatabase,
    val context: Context
) : CapitalRepository {

    override suspend fun saveCapital(capital: CapitalModel) {
        database.capitalDao().resetLastSelected()
        val existingCapitalEntity = database.capitalDao().getCapitalByName(capital.name)

        if (existingCapitalEntity != null) {
            val updatedCapital = existingCapitalEntity.copy(isLastSelected = true)
            database.capitalDao().update(updatedCapital)
        }
    }

    override suspend fun getAllCapitals(): List<CapitalModel> {
        initializeDataIfNeeded()

        return database
            .capitalDao()
            .getAllCapitals()
            .map { item ->
                item.toDomain()
            }
    }

    override suspend fun getLastCapital(): CapitalModel {
        val capitalEntity = database.capitalDao().getLastSelectedCapital()
        return capitalEntity?.toDomain() ?: getFirstCapital()
    }

    private suspend fun getFirstCapital(): CapitalModel {
        val firstCapitalEntity = database
            .capitalDao()
            .getFirstCapitalAlphabetically()
        return firstCapitalEntity.toDomain()
    }

    private suspend fun initializeDataIfNeeded() {
        val isFirstRun = checkFirstRun()

        if (isFirstRun) {
            context.capitals().forEach { capitalModel ->
                database
                    .capitalDao()
                    .insert(capitalModel.toDTO())
            }
            markInitialized()
        }
    }

    private fun checkFirstRun(): Boolean {
        val sharedPreferences =
            context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean("isFirstRun", true)
    }

    private fun markInitialized() {
        val sharedPreferences =
            context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE)
        sharedPreferences.edit().putBoolean("isFirstRun", false).apply()
    }
}