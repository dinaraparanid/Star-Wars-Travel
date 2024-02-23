package com.paranid5.star_wars_travel.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import app.cash.sqldelight.db.SqlDriver
import com.paranid5.star_wars_travel.data.ktor.PlanetsNetSource
import com.paranid5.star_wars_travel.data.ktor.PlanetsNetSourceImpl
import com.paranid5.star_wars_travel.data.paging.PlanetPagingSource
import com.paranid5.star_wars_travel.data.sqldelight.PlanetsDbSource
import com.paranid5.star_wars_travel.data.sqldelight.PlanetsDbSourceImpl
import io.ktor.client.HttpClient

private const val PAGE_SIZE = 10

class PlanetsRepository(ktorClient: HttpClient, sqlDriver: SqlDriver) : PlanetsNetSource by PlanetsNetSourceImpl(ktorClient), PlanetsDbSource by PlanetsDbSourceImpl(sqlDriver) {
    val planetsPagedFlow
        get() = Pager(
            config = PagingConfig(pageSize = PAGE_SIZE),
            pagingSourceFactory = {
                PlanetPagingSource(this)
            }
        ).flow
}