package com.paranid5.star_wars_travel.planets.presentation.planets.region

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paranid5.star_wars_travel.navigation.component.planets.PlanetsComponent
import com.paranid5.star_wars_travel.resources.R
import com.paranid5.star_wars_travel.resources.ui.StarWarsYellow

@Composable
internal fun RegionSelector(
    region: String?,
    isSelected: Boolean,
    planetsComponent: PlanetsComponent,
    modifier: Modifier = Modifier
) {
    val allItem = stringResource(R.string.all)

    val backgroundColor by remember(isSelected) {
        derivedStateOf { if (isSelected) Color.Black else Color.White }
    }

    val textColor by remember(isSelected) {
        derivedStateOf { if (isSelected) StarWarsYellow else Color.Black }
    }

    Card(
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        ),
        modifier = modifier
            .widthIn(min = 60.dp)
            .clickable { planetsComponent.reselectRegion(region) },
    ) {
        Text(
            text = region ?: allItem,
            color = textColor,
            fontSize = 12.sp,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 4.dp, bottom = 2.dp, start = 8.dp, end = 8.dp)
        )
    }
}