package com.paranid5.star_wars_travel.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.paranid5.star_wars_travel.core.common.domain.entities.wookiepedia.WookiepediaPlanet
import com.paranid5.star_wars_travel.data.ktor.PlanetsNetSource

class PlanetPagingSource(private val netSource: PlanetsNetSource) : PagingSource<Int, WookiepediaPlanet>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, WookiepediaPlanet> =
        try {
            val currentPage = params.key ?: 1
            val planetPage = netSource.fetchPlanetPage(currentPage)

            LoadResult.Page(
                data = planetPage.planets,
                prevKey = planetPage.previous?.let { currentPage - 1 },
                nextKey = planetPage.next?.let { currentPage + 1 }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }

    override fun getRefreshKey(state: PagingState<Int, WookiepediaPlanet>): Int? =
        state.anchorPosition
}