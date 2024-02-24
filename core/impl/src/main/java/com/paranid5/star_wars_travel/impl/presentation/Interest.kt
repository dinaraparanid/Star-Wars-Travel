package com.paranid5.star_wars_travel.impl.presentation

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.paranid5.star_wars_travel.core.common.domain.entities.wookiepedia.Interest
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

@Immutable
@Parcelize
@Serializable
data class Interest(
    val value: String,
    val coverUrl: String?
) : Parcelable {
    constructor(entity: Interest) : this(
        value = entity.value,
        coverUrl = entity.coverUrl
    )
}
