package com.paranid5.star_wars_travel.impl

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.paranid5.star_wars_travel.core.common.presentation.Theme
import com.paranid5.star_wars_travel.core.common.presentation.ThemeProvider
import com.paranid5.star_wars_travel.impl.di.themeModule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.component.get
import org.koin.core.context.startKoin
import org.koin.test.KoinTest

@RunWith(AndroidJUnit4::class)
class DiTest : KoinTest {
    @Test
    fun injectThemeTest() {
        startKoin { modules(themeModule) }
        val themeProvider = get<ThemeProvider>()
        assert(themeProvider.themeState.value == Theme.DARK)
    }
}