package com.paranid5.star_wars_travel.data.ktor

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.UserAgent
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.ANDROID
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal const val USER_AGENT =
    "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.98 Safari/537.36"

fun KtorClient() = HttpClient(OkHttp) {
    install(UserAgent) {
        agent = USER_AGENT
    }

    install(ContentNegotiation) {
        json(Json { ignoreUnknownKeys = true })
    }

    install(Logging) {
        logger = Logger.ANDROID
        level = LogLevel.ALL
    }
}