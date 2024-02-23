package com.paranid5.star_wars_travel.planets.presentation.planets.item

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.paranid5.star_wars_travel.impl.presentation.PlanetUiState

@Composable
internal fun PlanetItem(
    planet: PlanetUiState,
    modifier: Modifier = Modifier
) = ConstraintLayout(modifier) {
    val (cover, description) = createRefs()

    PlanetCover(
        planet = planet,
        modifier = Modifier.constrainAs(cover) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }
    )

    Box(
        Modifier.constrainAs(description) {
            bottom.linkTo(parent.bottom, margin = 8.dp)
            start.linkTo(parent.start, margin = 8.dp)
            end.linkTo(parent.end, margin = 8.dp)
            width = Dimension.fillToConstraints
        }
    ) {
        PlanetDescription(
            planet = planet,
            modifier = Modifier.align(Alignment.CenterStart)
        )

        TravelButton(
            planet = planet,
            modifier = Modifier.align(Alignment.BottomEnd)
        )
    }
}
