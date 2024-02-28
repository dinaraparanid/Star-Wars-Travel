package com.paranid5.star_wars_travel.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.paranid5.star_wars_travel.core.common.domain.entities.PlanetPage
import com.paranid5.star_wars_travel.data.di.dataModule
import com.paranid5.star_wars_travel.data.ktor.getPlanets
import io.ktor.client.HttpClient
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.get

private const val TATOOINE_DESCRIPTION = "Tatooine was a sparsely inhabited circumbinary desert planet located in the galaxy's Outer Rim Territories. Part of a binary star system, the planet orbited two scorching suns, resulting in the world lacking the necessary surface water to sustain large populations. As a result, many residents of the planet instead drew water from the atmosphere via moisture farms. The planet also had little surface vegetation. It was the homeworld to the native Jawa and Tusken Raider species and of Anakin and Luke Skywalker, who would go on to shape galactic history."

private val TATOOINE_REGIONS = listOf("Outer Rim Territories", "Galactic Frontier", "The Slice", "Spice Triangle")

private const val TATOOINE_ATMOSPHERE = "Breathable"

private const val TATOOINE_POPULATION = 200000L

@RunWith(AndroidJUnit4::class)
class WookieTest : KoinTest {
    private inline val context
        get() = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun getWookiePagesTest() = runBlocking {
        startKoin {
            androidContext(context.applicationContext)
            modules(dataModule)
        }

        val ktorClient = get<HttpClient>()

        val err = ktorClient.getPlanets(0)
        assert(err == PlanetPage(null, null, emptyList()))

        val page = ktorClient.getPlanets(1)

        assert(page.previous == null)
        assert(page.next == "https://swapi.dev/api/planets/?page=2")
        assert(page.planets.isNotEmpty())

        val tatooine = page.planets.first()

        assert(tatooine.title == "Tatooine")
        assert(tatooine.pageNumber == 1)
        assert(tatooine.description == TATOOINE_DESCRIPTION)
        assert(tatooine.astrographicalInformation.region == TATOOINE_REGIONS)
        assert(tatooine.physicalInformation.atmosphere == TATOOINE_ATMOSPHERE)
        assert(tatooine.societalInformation.population == TATOOINE_POPULATION)
    }
}