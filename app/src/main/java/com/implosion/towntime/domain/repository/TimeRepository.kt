package com.implosion.towntime.domain.repository

import kotlinx.coroutines.flow.Flow

interface TimeRepository {

    fun getUpdatedTime(): Flow<String>
}