package com.paranid5.star_wars_travel.impl.di

import com.paranid5.star_wars_travel.core.common.presentation.ThemeProvider
import com.paranid5.star_wars_travel.impl.presentation.ThemeProviderImpl
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val themeModule = module {
    singleOf(::bindThemeProvider)
}

private fun bindThemeProvider(): ThemeProvider =
    ThemeProviderImpl()