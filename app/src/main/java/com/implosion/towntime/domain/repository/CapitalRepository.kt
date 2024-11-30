package com.implosion.towntime.domain.repository

import com.implosion.towntime.domain.CapitalModel

interface CapitalRepository {

    suspend fun saveCapital(capital: CapitalModel)

    suspend fun getAllCapitals(): List<CapitalModel>

    suspend fun getLastCapital(): CapitalModel
}