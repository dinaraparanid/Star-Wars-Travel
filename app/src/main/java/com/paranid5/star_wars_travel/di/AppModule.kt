package com.paranid5.star_wars_travel.di

import com.paranid5.star_wars_travel.data.di.dataModule
import com.paranid5.star_wars_travel.impl.di.themeModule
import org.koin.dsl.module

val appModule = module {
    includes(dataModule, themeModule)
}