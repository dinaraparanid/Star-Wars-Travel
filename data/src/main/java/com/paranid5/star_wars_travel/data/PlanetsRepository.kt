package com.paranid5.star_wars_travel.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.paranid5.star_wars_travel.core.common.domain.entities.wookiepedia.Interest
import com.paranid5.star_wars_travel.data.ktor.PlanetsNetSource
import com.paranid5.star_wars_travel.data.paging.PlanetPagingSource
import com.paranid5.star_wars_travel.data.sqldelight.PlanetsDbSource

private const val PAGE_SIZE = 10

class PlanetsRepository(
    private val netSource: PlanetsNetSource,
    private val dbSource: PlanetsDbSource,
) {
    val planetsPagedFlow
        get() = Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, prefetchDistance = PAGE_SIZE / 2),
            pagingSourceFactory = {
                PlanetPagingSource(netSource, dbSource)
            }
        ).flow

    fun updateInterestsAsync(interests: List<Interest>) =
        dbSource.updateInterestsAsync(interests)
}