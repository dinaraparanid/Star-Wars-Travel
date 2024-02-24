package com.paranid5.star_wars_travel.settings.di

import com.paranid5.star_wars_travel.settings.presentation.SettingsViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val settingsModule = module {
    singleOf(::SettingsViewModel)
}