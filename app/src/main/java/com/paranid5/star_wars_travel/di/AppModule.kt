package com.paranid5.star_wars_travel.di

import com.paranid5.star_wars_travel.planets.di.planetsModule
import org.koin.dsl.module

val appModule = module {
    includes(planetsModule)
}