package com.paranid5.star_wars_travel.data

import app.cash.sqldelight.db.SqlDriver
import com.paranid5.star_wars_travel.data.ktor.PlanetsNetSource
import com.paranid5.star_wars_travel.data.ktor.PlanetsNetSourceImpl
import com.paranid5.star_wars_travel.data.sqldelight.PlanetsDbSource
import com.paranid5.star_wars_travel.data.sqldelight.PlanetsDbSourceImpl
import io.ktor.client.HttpClient

class PlanetsRepository(ktorClient: HttpClient, sqlDriver: SqlDriver) :
    PlanetsNetSource by PlanetsNetSourceImpl(ktorClient),
    PlanetsDbSource by PlanetsDbSourceImpl(sqlDriver)