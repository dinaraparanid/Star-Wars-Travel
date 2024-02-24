package com.paranid5.star_wars_travel.planets.presentation.planet.interest

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.paranid5.star_wars_travel.impl.presentation.Interest
import com.paranid5.star_wars_travel.planets.presentation.coverModel
import com.paranid5.star_wars_travel.resources.R
import com.paranid5.star_wars_travel.resources.ui.LocalAppColors

@Composable
fun InterestItem(
    interest: Interest,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val colors = LocalAppColors.current

    Column(modifier) {
        AsyncImage(
            model = coverModel(
                coverUrl = interest.coverUrl,
                context = context,
                isPlaceholderRequired = true
            ),
            contentDescription = stringResource(R.string.interest_preview),
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally)
        )

        Spacer(Modifier.height(2.dp))

        Text(
            text = interest.value,
            fontSize = 10.sp,
            color = colors.onBackground,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
    }
}