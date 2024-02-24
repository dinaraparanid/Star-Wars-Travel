package com.paranid5.star_wars_travel.planets.presentation.planets.item

import androidx.compose.runtime.Composable
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

    ConstraintLayout(
        Modifier.constrainAs(description) {
            bottom.linkTo(parent.bottom, margin = 8.dp)
            start.linkTo(parent.start, margin = 8.dp)
            end.linkTo(parent.end, margin = 8.dp)
            width = Dimension.fillToConstraints
        }
    ) {
        val (desc, travel) = createRefs()

        PlanetDescription(
            planet = planet,
            modifier = Modifier.constrainAs(desc) {
                centerVerticallyTo(parent)
                start.linkTo(parent.start)
                end.linkTo(travel.start, margin = 8.dp)
                width = Dimension.fillToConstraints
            }
        )

        TravelButton(
            planet = planet,
            modifier = Modifier.constrainAs(travel) {
                bottom.linkTo(parent.bottom)
                end.linkTo(parent.end)
            }
        )
    }
}
