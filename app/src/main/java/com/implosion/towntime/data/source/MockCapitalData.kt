package com.implosion.towntime.data.source

import android.content.Context
import com.implosion.towntime.R
import com.implosion.towntime.domain.CapitalModel

//Типо откуда-то пришло или где-то лежит
fun Context.capitals() = listOf(
    CapitalModel(name = getString(R.string.capital_moscow), timeZone = "Europe/Moscow"),
    CapitalModel(name = getString(R.string.capital_stockholm), timeZone = "Europe/Stockholm"),
    CapitalModel(name = getString(R.string.capital_london), timeZone = "Europe/London"),
    CapitalModel(name = getString(R.string.capital_berlin), timeZone = "Europe/Berlin"),
    CapitalModel(name = getString(R.string.capital_paris), timeZone = "Europe/Paris")
)