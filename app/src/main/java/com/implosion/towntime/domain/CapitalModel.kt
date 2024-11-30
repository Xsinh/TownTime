package com.implosion.towntime.domain

import com.implosion.towntime.data.database.entity.CapitalEntity

data class CapitalModel(
    val name: String,
    val timeZone: String
)

fun CapitalModel.toDTO(): CapitalEntity {
    return CapitalEntity(
        name = this.name,
        timeZone = this.timeZone
    )
}