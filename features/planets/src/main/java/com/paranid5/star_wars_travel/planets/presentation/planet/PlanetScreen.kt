package com.paranid5.star_wars_travel.planets.presentation.planet

import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.paranid5.star_wars_travel.resources.R
import com.paranid5.star_wars_travel.impl.presentation.PlanetUiState
import com.paranid5.star_wars_travel.navigation.component.PlanetComponent
import com.paranid5.star_wars_travel.planets.presentation.planet.interest.InterestList
import com.paranid5.star_wars_travel.planets.presentation.planets.item.PlanetCover
import com.paranid5.star_wars_travel.planets.presentation.planets.item.PlanetTitle
import com.paranid5.star_wars_travel.planets.presentation.planets.item.TravelButton

@Composable
fun PlanetScreen(
    planet: PlanetUiState,
    planetComponent: PlanetComponent,
    modifier: Modifier = Modifier
) = ConstraintLayout(modifier.verticalScroll(rememberScrollState())) {
    val (
        title,
        cover,
        travelButton,
        interests,
        description,
        astroInfo,
        physInfo,
        socInfo
    ) = createRefs()

    PlanetTitle(
        planetTitle = planet.title,
        fontSize = 24.sp,
        modifier = Modifier
            .zIndex(10F)
            .constrainAs(title) {
                bottom.linkTo(travelButton.top, margin = 8.dp)
                start.linkTo(travelButton.start)
            }
    )

    PlanetCover(
        planet = planet,
        roundedCorners = 0.dp,
        modifier = Modifier
            .aspectRatio(1F)
            .constrainAs(cover) {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                width = Dimension.fillToConstraints
            }
    )

    TravelButton(
        planet = planet,
        modifier = Modifier.constrainAs(travelButton) {
            centerAround(cover.bottom)
            start.linkTo(cover.start, margin = 32.dp)
            end.linkTo(cover.end, margin = 32.dp)
            width = Dimension.fillToConstraints
        }
    )

    InterestList(
        planet = planet,
        planetComponent = planetComponent,
        modifier = Modifier.constrainAs(interests) {
            top.linkTo(travelButton.bottom, margin = 16.dp)
            start.linkTo(parent.start, margin = 16.dp)
        }
    )

    Description(
        planetDescription = planet.description ?: stringResource(R.string.no_description),
        modifier = Modifier.constrainAs(description) {
            top.linkTo(interests.bottom, margin = 16.dp)
            start.linkTo(parent.start, margin = 16.dp)
            end.linkTo(parent.end, margin = 16.dp)
            width = Dimension.fillToConstraints
        }
    )

    InfoMenu(
        mainLabel = stringResource(R.string.astro_info),
        items = planet.astrographicalInformation.toEntryList(),
        modifier = Modifier.constrainAs(astroInfo) {
            top.linkTo(description.bottom, margin = 16.dp)
            start.linkTo(parent.start, margin = 16.dp)
            end.linkTo(parent.end, margin = 16.dp)
            width = Dimension.fillToConstraints
        }
    )

    InfoMenu(
        mainLabel = stringResource(R.string.phys_info),
        items = planet.physicalInformation.toEntryList(),
        modifier = Modifier.constrainAs(physInfo) {
            top.linkTo(astroInfo.bottom, margin = 16.dp)
            start.linkTo(parent.start, margin = 16.dp)
            end.linkTo(parent.end, margin = 16.dp)
            width = Dimension.fillToConstraints
        }
    )

    InfoMenu(
        mainLabel = stringResource(R.string.soc_info),
        items = planet.societalInformation.toEntryList(),
        modifier = Modifier.constrainAs(socInfo) {
            top.linkTo(physInfo.bottom, margin = 16.dp)
            start.linkTo(parent.start, margin = 16.dp)
            end.linkTo(parent.end, margin = 16.dp)
            width = Dimension.fillToConstraints
        }
    )
}