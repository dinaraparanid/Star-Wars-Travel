package com.paranid5.star_wars_travel.planets.presentation.planet

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paranid5.star_wars_travel.impl.presentation.AstroInfo
import com.paranid5.star_wars_travel.impl.presentation.PhysInfo
import com.paranid5.star_wars_travel.impl.presentation.SocInfo
import com.paranid5.star_wars_travel.planets.presentation.HeaderText
import com.paranid5.star_wars_travel.planets.presentation.LabelList
import com.paranid5.star_wars_travel.resources.R
import com.paranid5.star_wars_travel.resources.ui.LocalAppColors
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

internal typealias InfoItems = List<Pair<String, List<String>>>

@Composable
internal fun InfoMenu(
    mainLabel: String,
    items: InfoItems,
    modifier: Modifier = Modifier
) {
    val colors = LocalAppColors.current

    var isMenuShown by remember {
        mutableStateOf(true)
    }

    Column(modifier) {
        HeaderText(text = mainLabel)

        if (isMenuShown)
            Spacer(Modifier.height(16.dp))

        LabelList(
            items = items,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = if (isMenuShown) Dp.Unspecified else 0.dp)
                .animateContentSize()
        )

        Spacer(Modifier.height(4.dp))

        Text(
            text = stringResource(
                when {
                    isMenuShown -> R.string.hide
                    else -> R.string.show
                }
            ),
            color = colors.transparentUtility,
            fontSize = 10.sp,
            modifier = Modifier.clickable {
                isMenuShown = !isMenuShown
            }
        )
    }
}

@Composable
internal fun AstroInfo.toEntryList() =
    persistentListOf(
        stringResource(R.string.region) to region.toList(),
        sector?.let { stringResource(R.string.sector) to listOf(it) },
        system?.let { stringResource(R.string.system) to listOf(it) },
        moons.takeIf { it > 0 }?.let { stringResource(R.string.moons) to listOf(it.toString()) },
        stringResource(R.string.rotation_period) to listOf(rotationPeriod.toString()),
        stringResource(R.string.orbital_period) to listOf(orbitalPeriod.toString()),
        stringResource(R.string.trade_routes) to tradeRoutes.toList()
    ).filterNotNullOrEmpty()

@Composable
internal fun PhysInfo.toEntryList() =
    persistentListOf(
        planetClass?.let { stringResource(R.string.planet_class) to listOf(it) },
        stringResource(R.string.climate) to listOf(climate),
        diameter.takeIf { it > 0 }?.let { stringResource(R.string.diameter) to listOf(it.toString()) },
        stringResource(R.string.gravity) to listOf(gravity),
        atmosphere?.let { stringResource(R.string.atmosphere) to listOf(it) },
        stringResource(R.string.terrain) to listOf(terrain),
        stringResource(R.string.surface_water) to listOf(surfaceWater.toString()),
        stringResource(R.string.flora) to flora.toList(),
        stringResource(R.string.fauna) to fauna.toList()
    ).filterNotNullOrEmpty()

@Composable
internal fun SocInfo.toEntryList() =
    persistentListOf(
        population.takeIf { it > 0 }?.let { stringResource(R.string.population) to listOf(it.toString()) },
        stringResource(R.string.native_spec) to nativeSpecies.toList(),
        stringResource(R.string.other_spec) to otherSpecies.toList(),
        stringResource(R.string.languages) to primaryLanguages.toList(),
        government?.let { stringResource(R.string.government) to listOf(it) },
        stringResource(R.string.major_cities) to majorCities.toList()
    ).filterNotNullOrEmpty()

private fun List<Pair<String, List<String>>?>.filterNotNullOrEmpty() =
    filterNotNull().filter { it.second.isNotEmpty() }.toImmutableList()