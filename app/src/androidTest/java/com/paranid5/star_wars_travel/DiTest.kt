package com.paranid5.star_wars_travel

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.paranid5.star_wars_travel.core.common.presentation.Theme
import com.paranid5.star_wars_travel.core.common.presentation.ThemeProvider
import com.paranid5.star_wars_travel.data.PlanetsRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.test.KoinTest
import org.koin.test.get

@RunWith(AndroidJUnit4::class)
class DiTest : KoinTest {
    @Test
    fun injectTest() {
        val planetsRepository1 = get<PlanetsRepository>()
        val planetsRepository2 = get<PlanetsRepository>()
        assert(planetsRepository1 === planetsRepository2)

        val themeProvider1 = get<ThemeProvider>()
        val themeProvider2 = get<ThemeProvider>()
        assert(themeProvider1 == themeProvider2)
        assert(themeProvider1.themeState.value == Theme.DARK)
    }
}