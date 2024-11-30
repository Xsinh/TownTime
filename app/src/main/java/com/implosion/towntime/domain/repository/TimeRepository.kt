package com.implosion.towntime.domain.repository

import kotlinx.coroutines.flow.Flow

interface TimeRepository {

    fun getUpdatedTime(capital: String): Flow<String>
}