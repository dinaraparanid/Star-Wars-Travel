package com.paranid5.star_wars_travel.planets.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.paranid5.star_wars_travel.planets.presentation.planet.InfoItems
import com.paranid5.star_wars_travel.resources.ui.TransparentUtility

@Composable
fun LabelList(items: InfoItems, modifier: Modifier = Modifier) =
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        items.forEachIndexed { i, (label, values) ->
            Column(Modifier.fillMaxWidth()) {
                Row(Modifier.fillMaxWidth()) {
                    Label(
                        text = label,
                        modifier = Modifier.weight(1F)
                    )

                    LabelValues(
                        values = values,
                        modifier = Modifier.weight(1F)
                    )
                }

                if (i < items.lastIndex)
                    Spacer(
                        Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(TransparentUtility)
                    )
            }
        }
    }