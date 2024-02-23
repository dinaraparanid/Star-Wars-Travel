package com.paranid5.star_wars_travel.data.sqldelight

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import app.cash.sqldelight.db.SqlDriver
import com.paranid5.star_wars_travel.core.common.entities.wookiepedia.AstrographicalInformation
import com.paranid5.star_wars_travel.core.common.entities.wookiepedia.PhysicalInformation
import com.paranid5.star_wars_travel.core.common.entities.wookiepedia.SocietalInformation
import com.paranid5.star_wars_travel.core.common.entities.wookiepedia.WookiepediaPlanet
import com.paranid5.star_wars_travel.data.Planets
import com.paranid5.starwarstravel.data.PlanetsQueries
import com.paranid5.starwarstravel.data.SelectBaseItems
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

internal class PlanetsDbSourceImpl(driver: SqlDriver) :
    PlanetsDbSource,
    CoroutineScope by CoroutineScope(Dispatchers.IO) {
    private val database by lazy {
        Planets(driver)
    }

    private val queries by lazy {
        database.planetsQueries
    }

    private val mutex by lazy { Mutex() }

    @OptIn(ExperimentalCoroutinesApi::class)
    override val planetsFlow
        get() = queries
            .selectBaseItems()
            .asFlow()
            .mapToList(Dispatchers.IO)
            .mapLatest { items ->
                items.map { item ->
                    WookiepediaPlanet(
                        title = item.title,
                        description = item.description,
                        coverUrl = item.coverUrl,
                        astrographicalInformation = queries.parseAstroInfo(item),
                        physicalInformation = queries.parsePhysicalInfo(item),
                        societalInformation = queries.parseSocInfo(item)
                    )
                }
            }

    override fun addPlanetAsync(planet: WookiepediaPlanet): Job =
        launch(Dispatchers.IO) {
            mutex.withLock { queries.addPlanet(planet) }
        }
}

internal fun PlanetsQueries.parseAstroInfo(item: SelectBaseItems) =
    AstrographicalInformation(
        rotationPeriod = item.rotationPeriod.toInt(),
        orbitalPeriod = item.orbitalPeriod.toInt(),
        sector = item.sector,
        system = item.system,
        moons = item.moons.toInt(),
        region = selectRegions(astroInfoId = item.astroInfoId)
            .executeAsList()
            .mapNotNull { it.region },
        tradeRoutes = selectTradeRoutes(astroInfoId = item.astroInfoId)
            .executeAsList()
            .mapNotNull { it.tradeRoute }
    )

internal fun PlanetsQueries.parsePhysicalInfo(item: SelectBaseItems) =
    PhysicalInformation(
        climate = item.climate,
        gravity = item.gravity,
        terrain = item.terrain,
        surfaceWater = item.surface.toIntOrNull() ?: 0,
        diameter = item.diameter.toInt(),
        planetClass = item.planetClass,
        atmosphere = item.atmosphere,
        interest = selectInterests(physInfoId = item.physInfoId)
            .executeAsList()
            .mapNotNull { it.interest },
        flora = selectFlora(physInfoId = item.physInfoId)
            .executeAsList()
            .mapNotNull { it.flora },
        fauna = selectFauna(physInfoId = item.physInfoId)
            .executeAsList()
            .mapNotNull { it.fauna }
    )

internal fun PlanetsQueries.parseSocInfo(item: SelectBaseItems) =
    SocietalInformation(
        population = item.population.toInt(),
        government = item.government,
        nativeSpecies = selectNativeSpecies(socInfoId = item.socInfoId)
            .executeAsList()
            .mapNotNull { it.nativeSpecies },
        otherSpecies = selectOtherSpecies(socInfoId = item.socInfoId)
            .executeAsList()
            .mapNotNull { it.otherSpecies },
        primaryLanguages = selectLanguages(socInfoId = item.socInfoId)
            .executeAsList()
            .mapNotNull { it.language },
        majorCities = selectCities(socInfoId = item.socInfoId)
            .executeAsList()
            .mapNotNull { it.city }
    )

internal fun PlanetsQueries.insertAstroInfo(info: AstrographicalInformation): Long {
    insertAstroInfo(
        rotationPeriod = info.rotationPeriod.toLong(),
        orbitalPeriod = info.orbitalPeriod.toLong(),
        sector = info.sector,
        system = info.system,
        moons = info.moons.toLong()
    )

    return insertedAstroInfo().executeAsOne()
}

internal fun PlanetsQueries.insertPhysInfo(info: PhysicalInformation): Long {
    insertPhysInfo(
        climate = info.climate,
        gravity = info.gravity,
        terrain = info.terrain,
        surfaceWater = info.surfaceWater.toString(),
        diameter = info.diameter.toLong(),
        planetClass = info.planetClass,
        atmosphere = info.atmosphere
    )

    return insertedPhysInfo().executeAsOne()
}

internal fun PlanetsQueries.insertSocInfo(info: SocietalInformation): Long {
    insertSocInfo(
        population = info.population.toLong(),
        government = info.government
    )

    return insertedPhysInfo().executeAsOne()
}

internal fun PlanetsQueries.insertPlanet(
    planet: WookiepediaPlanet,
    astroInfoId: Long,
    physInfoId: Long,
    socInfoId: Long,
) = insertPlanet(
    title = planet.title,
    description = planet.description,
    coverUrl = planet.coverUrl,
    astroInfoId = astroInfoId,
    physInfoId = physInfoId,
    socInfoId = socInfoId
)

internal fun PlanetsQueries.addPlanet(planet: WookiepediaPlanet) = runCatching {
    transaction {
        val astroInfoId = insertAstroInfo(planet.astrographicalInformation)

        planet.astrographicalInformation.region.forEach {
            insertRegion(it)
            insertAstroInfoRegion(astroInfoId, it)
        }

        planet.astrographicalInformation.tradeRoutes.forEach {
            insertTradeRoute(it)
            insertAstroInfoTradeRoute(astroInfoId, it)
        }

        val physInfoId = insertPhysInfo(planet.physicalInformation)

        planet.physicalInformation.interest.forEach {
            insertInterest(it)
            insertPhysInfoInterest(physInfoId, it)
        }

        planet.physicalInformation.flora.forEach {
            insertInterest(it)
            insertPhysInfoInterest(physInfoId, it)
        }

        planet.physicalInformation.fauna.forEach {
            insertInterest(it)
            insertPhysInfoInterest(physInfoId, it)
        }

        val socInfoId = insertSocInfo(planet.societalInformation)

        planet.societalInformation.nativeSpecies.forEach {
            insertNativeSpecies(it)
            insertSocInfoNativeSpecied(socInfoId, it)
        }

        planet.societalInformation.otherSpecies.forEach {
            insertOtherSpecies(it)
            insertSocInfoOtherSpecies(socInfoId, it)
        }

        planet.societalInformation.primaryLanguages.forEach {
            insertLanguage(it)
            insertSocInfoLanguage(socInfoId, it)
        }

        planet.societalInformation.majorCities.forEach {
            insertCity(it)
            insertSocInfoCity(socInfoId, it)
        }

        insertPlanet(
            planet = planet,
            astroInfoId = astroInfoId,
            physInfoId = physInfoId,
            socInfoId = socInfoId
        )
    }
}