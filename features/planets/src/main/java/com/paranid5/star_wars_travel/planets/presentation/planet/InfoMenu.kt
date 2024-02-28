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
import com.paranid5.star_wars_travel.core.common.domain.use_case.prettifyNumber
import com.paranid5.star_wars_travel.impl.presentation.AstroInfo
import com.paranid5.star_wars_travel.impl.presentation.PhysInfo
import com.paranid5.star_wars_travel.impl.presentation.SocInfo
import com.paranid5.star_wars_travel.planets.presentation.HeaderText
import com.paranid5.star_wars_travel.planets.presentation.LabelList
import com.paranid5.star_wars_travel.resources.R
import com.paranid5.star_wars_travel.resources.ui.LocalAppColors
import kotlinx.collections.immutable.ImmutableList
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
        stringResource(R.string.region) to region.toImmutableList(),
        sector?.let { stringResource(R.string.sector) to persistentListOf(it) },
        system?.let { stringResource(R.string.system) to persistentListOf(it) },
        moons.takeIf { it > 0 }?.let { stringResource(R.string.moons) to persistentListOf(it.toString()) },
        stringResource(R.string.rotation_period) to persistentListOf(rotationPeriod.toString()),
        stringResource(R.string.orbital_period) to persistentListOf(orbitalPeriod.toString()),
        stringResource(R.string.trade_routes) to tradeRoutes.toImmutableList()
    ).filterNotNullOrEmpty()

@Composable
internal fun PhysInfo.toEntryList() =
    persistentListOf(
        planetClass?.let { stringResource(R.string.planet_class) to persistentListOf(it) },
        stringResource(R.string.climate) to persistentListOf(climate),
        diameter.takeIf { it > 0 }?.let {
            stringResource(R.string.diameter) to persistentListOf(prettifyNumber(it.toString()))
        },
        stringResource(R.string.gravity) to persistentListOf(gravity),
        atmosphere?.let { stringResource(R.string.atmosphere) to persistentListOf(it) },
        stringResource(R.string.terrain) to persistentListOf(terrain),
        stringResource(R.string.surface_water) to persistentListOf(surfaceWater.toString()),
        stringResource(R.string.flora) to flora.toImmutableList(),
        stringResource(R.string.fauna) to fauna.toImmutableList()
    ).filterNotNullOrEmpty()

@Composable
internal fun SocInfo.toEntryList() =
    persistentListOf(
        population.takeIf { it > 0 }?.let {
            stringResource(R.string.population) to persistentListOf(prettifyNumber(it.toString()))
        },
        stringResource(R.string.native_spec) to nativeSpecies.toImmutableList(),
        stringResource(R.string.other_spec) to otherSpecies.toImmutableList(),
        stringResource(R.string.languages) to primaryLanguages.toImmutableList(),
        government?.let { stringResource(R.string.government) to persistentListOf(it) },
        stringResource(R.string.major_cities) to majorCities.toImmutableList()
    ).filterNotNullOrEmpty()

private fun ImmutableList<Pair<String, ImmutableList<String>>?>.filterNotNullOrEmpty() =
    filterNotNull().filter { it.second.isNotEmpty() }.toImmutableList()