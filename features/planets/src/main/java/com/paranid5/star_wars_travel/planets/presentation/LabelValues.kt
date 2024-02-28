package com.paranid5.star_wars_travel.planets.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paranid5.star_wars_travel.resources.ui.LocalAppColors

@Composable
internal fun LabelValues(values: List<String>, modifier: Modifier = Modifier) =
    when (values.size) {
        1 -> SingleValue(
            value = values.first(),
            modifier = modifier
        )

        else -> ValuesList(
            values = values,
            modifier = modifier
        )
    }

@Composable
private fun SingleValue(value: String, modifier: Modifier = Modifier) {
    val colors = LocalAppColors.current

    Text(
        text = value,
        modifier = modifier,
        color = colors.onBackground,
        fontSize = 14.sp,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
private fun ValuesList(values: List<String>, modifier: Modifier = Modifier) =
    Column(modifier) {
        values.forEach { ValuesItem(value = it) }
    }

@Composable
private fun ValuesItem(value: String, modifier: Modifier = Modifier) {
    val colors = LocalAppColors.current

    Row(modifier) {
        Canvas(Modifier.align(Alignment.CenterVertically)) {
            drawCircle(
                color = colors.onBackground,
                radius = 6F
            )
        }

        Spacer(Modifier.width(8.dp))

        SingleValue(
            value = value,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}