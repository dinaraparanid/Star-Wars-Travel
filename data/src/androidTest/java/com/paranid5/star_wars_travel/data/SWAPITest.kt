package com.paranid5.star_wars_travel.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import app.cash.sqldelight.db.SqlDriver
import com.paranid5.star_wars_travel.data.di.dataModule
import com.paranid5.star_wars_travel.data.ktor.swapi.getSwapiPlanets
import io.ktor.client.HttpClient
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.get

@RunWith(AndroidJUnit4::class)
class SWAPITest : KoinTest {
    private inline val context
        get() = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun getSwapiPageTest() = runBlocking {
        startKoin {
            androidContext(context.applicationContext)
            modules(dataModule)
        }

        val ktorClient = get<HttpClient>()
        val res = ktorClient.getSwapiPlanets(1)

        assert(res.isSuccess)

        val page = res.getOrNull()!!

        assert(page.previous == null)
        assert(page.next == "https://swapi.dev/api/planets/?page=2")
        assert(page.results.isNotEmpty())
        assert(page.results.first().name == "Tatooine")
    }
}