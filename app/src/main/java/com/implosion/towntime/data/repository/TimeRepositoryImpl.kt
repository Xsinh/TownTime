package com.implosion.towntime.data.repository

import android.util.Log
import com.implosion.towntime.data.network.TimeApi
import com.implosion.towntime.domain.repository.TimeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.withContext
import kotlinx.coroutines.yield
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField

class TimeRepositoryImpl(private val apiService: TimeApi) : TimeRepository {


    // Функция для получения обновленного времени каждую секунду
    override fun getUpdatedTime(): Flow<String> = flow {
        val response = apiService.getData("Europe/Berlin")
        if (response.isSuccessful) {
            //Log.d("###", response.body().toString())
            val response = response.body()
            val initialTime = ZonedDateTime.parse(response?.dateTime)
            val targetZoneId = ZoneId.of(response?.timeZone)


            val timeFormatter = DateTimeFormatterBuilder()
                .appendPattern("yyyy-MM-dd'T'HH:mm:ss")
                .optionalStart()
                .appendFraction(ChronoField.NANO_OF_SECOND, 0, 9, true)
                .optionalEnd()
                .toFormatter()

            var currentTime = initialTime.withZoneSameInstant(targetZoneId)

            while (true) {
                // Эмитируем текущее время в форматированной строке
                emit(currentTime.format(timeFormatter))

                // Ждем одну секунду
                delay(1000)

                // Обновляем время
                currentTime = currentTime.plusSeconds(1)
            }
        }
    }
}