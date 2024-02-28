package com.paranid5.star_wars_travel.impl.presentation

import androidx.compose.runtime.Immutable
import com.paranid5.star_wars_travel.core.common.domain.entities.wookiepedia.Interest
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class Interest(
    val value: String,
    val coverUrl: String?
) {
    constructor(entity: Interest) : this(
        value = entity.value,
        coverUrl = entity.coverUrl
    )
}
