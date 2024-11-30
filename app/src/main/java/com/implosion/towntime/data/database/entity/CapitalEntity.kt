package com.implosion.towntime.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.implosion.towntime.data.database.entity.CapitalEntity.Companion.TABLE_NAME
import com.implosion.towntime.domain.CapitalModel

@Entity(tableName = TABLE_NAME)
data class CapitalEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val timeZone: String,
    val isLastSelected: Boolean = false
) {
    companion object {
        const val TABLE_NAME = "capitals"
    }
}

fun CapitalEntity.toDomain(): CapitalModel {
    return CapitalModel(
        name = this.name,
        timeZone = this.timeZone
    )
}