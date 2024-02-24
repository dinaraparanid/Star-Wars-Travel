package com.paranid5.star_wars_travel.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.paranid5.star_wars_travel.core.common.domain.entities.wookiepedia.WookiepediaPlanet
import com.paranid5.star_wars_travel.data.ktor.PlanetsNetSource
import com.paranid5.star_wars_travel.data.sqldelight.PlanetsDbSource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class PlanetPagingSource(
    private val netSource: PlanetsNetSource,
    private val dbSource: PlanetsDbSource,
) : PagingSource<Int, WookiepediaPlanet>(),
    CoroutineScope by CoroutineScope(Dispatchers.IO) {
    override suspend fun load(params: LoadParams<Int>) = runCatching {
        val currentPage = params.key ?: 1

        val fetchedPlanetsTask = async(Dispatchers.IO) {
            netSource
                .fetchCompletePlanetPage(currentPage)
                .apply { planets.forEach { dbSource.addPlanetAsync(it) } }
        }

        val planetPageTask = async(Dispatchers.IO) {
            netSource.fetchSwapiPlanetPage(currentPage)
        }

        val planetsTask = async(Dispatchers.IO) {
            dbSource.getPlanets()
        }

        val planetsRes = planetsTask.await()
        val planets = planetsRes.filter { it.pageNumber == currentPage }
        val pages = planetsRes.pagesSet()

        LoadResult.Page(
            data = when {
                planets.isEmpty() -> fetchedPlanetsTask.await().planets
                else -> planets
            },
            prevKey = when {
                currentPage - 1 in pages -> currentPage - 1
                else -> planetPageTask.await().next?.let { currentPage - 1 }
            },
            nextKey = when {
                currentPage + 1 in pages -> currentPage + 1
                else -> planetPageTask.await().next?.let { currentPage + 1 }
            }
        )
    }.fold(
        onSuccess = { it },
        onFailure = { LoadResult.Error(it) }
    )

    override fun getRefreshKey(state: PagingState<Int, WookiepediaPlanet>) =
        state.anchorPosition
}

internal fun List<WookiepediaPlanet>.pagesSet() =
    map { it.pageNumber }.toSet()
