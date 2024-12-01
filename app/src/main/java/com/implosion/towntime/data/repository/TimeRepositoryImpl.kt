package com.implosion.towntime.data.repository

import com.implosion.towntime.data.network.TimeApi
import com.implosion.towntime.domain.repository.TimeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.time.LocalDateTime
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField

class TimeRepositoryImpl(private val apiService: TimeApi) : TimeRepository {

    override fun getUpdatedTime(capital: String): Flow<String> = flow {
        try {
            val response = apiService.getData(capital)
            if (response.isSuccessful) {
                val responseBody = response.body()

                val initialLocalDateTime = LocalDateTime.parse(
                    responseBody?.dateTime, DateTimeFormatterBuilder()
                        .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
                        .optionalStart()
                        .appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true)
                        .optionalEnd()
                        .toFormatter()
                )

                var currentTime = initialLocalDateTime

                val timeFormatter = DateTimeFormatterBuilder()
                    .appendPattern("HH:mm:ss")
                    .toFormatter()

                while (true) {
                    emit(currentTime.format(timeFormatter))
                    delay(1000)
                    currentTime = currentTime.plusSeconds(1)
                }
            }
        } catch (ignored: Exception) {
        }
    }
}