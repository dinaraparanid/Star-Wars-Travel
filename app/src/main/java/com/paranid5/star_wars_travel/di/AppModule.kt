package com.paranid5.star_wars_travel.di

import com.paranid5.star_wars_travel.data.di.dataModule
import com.paranid5.star_wars_travel.impl.di.themeModule
import com.paranid5.star_wars_travel.planets.di.planetsModule
import com.paranid5.star_wars_travel.settings.di.settingsModule
import org.koin.dsl.module

val appModule = module {
    includes(dataModule, planetsModule, themeModule, settingsModule)
}