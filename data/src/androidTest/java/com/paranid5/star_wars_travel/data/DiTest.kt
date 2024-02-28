package com.paranid5.star_wars_travel.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import app.cash.sqldelight.db.SqlDriver
import com.paranid5.star_wars_travel.data.di.dataModule
import io.ktor.client.HttpClient
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.get

@RunWith(AndroidJUnit4::class)
class DiTest : KoinTest {
    private inline val context
        get() = InstrumentationRegistry.getInstrumentation().targetContext

    @Test
    fun injectTest() {
        startKoin {
            androidContext(context.applicationContext)
            modules(dataModule)
        }

        val ktorClient1 = get<HttpClient>()
        val ktorClient2 = get<HttpClient>()
        assert(ktorClient1 === ktorClient2)

        val sqlDelightClient1 = get<SqlDriver>()
        val sqlDelightClient2 = get<SqlDriver>()
        assert(sqlDelightClient1 === sqlDelightClient2)

        val planetsRepository1 = get<PlanetsRepository>()
        val planetsRepository2 = get<PlanetsRepository>()
        assert(planetsRepository1 === planetsRepository2)
    }
}