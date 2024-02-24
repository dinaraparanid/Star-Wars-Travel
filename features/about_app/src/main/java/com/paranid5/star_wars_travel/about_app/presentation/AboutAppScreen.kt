package com.paranid5.star_wars_travel.about_app.presentation

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paranid5.star_wars_travel.resources.R
import com.paranid5.star_wars_travel.resources.ui.LocalAppColors
import com.paranid5.star_wars_travel.resources.ui.starJediFont

@Composable
fun AboutAppScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val colors = LocalAppColors.current

    Column(modifier.verticalScroll(rememberScrollState())) {
        Spacer(Modifier.height(64.dp))

        Image(
            painter = painterResource(R.drawable.app_icon),
            contentDescription = stringResource(R.string.app_name),
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(16.dp))
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.app_name),
            fontSize = 32.sp,
            fontFamily = starJediFont,
            color = colors.onBackground,
            modifier = Modifier.align(Alignment.CenterHorizontally),
        )

        Spacer(Modifier.height(32.dp))

        Text(
            text = stringResource(R.string.developer),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = colors.onBackground,
            modifier = Modifier.align(Alignment.Start),
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "Paranid5",
            fontSize = 16.sp,
            color = colors.onBackground,
            style = TextStyle(textDecoration = TextDecoration.Underline),
            modifier = Modifier
                .align(Alignment.Start)
                .clickable { context.openMyGitHub() },
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = stringResource(R.string.app_description_1),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = colors.onBackground,
            modifier = Modifier.align(Alignment.Start),
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = stringResource(R.string.app_description_2),
            fontSize = 16.sp,
            color = colors.onBackground,
            modifier = Modifier.align(Alignment.Start),
        )

        Spacer(Modifier.height(16.dp))

        Button(
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = colors.onBackground),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = { context.openProjectGitHub() }
        ) {
            Icon(
                painter = painterResource(R.drawable.github),
                contentDescription = stringResource(R.string.available_on_github),
                tint = colors.background,
                modifier = Modifier
                    .size(32.dp)
                    .align(Alignment.CenterVertically)
            )

            Spacer(Modifier.width(8.dp))

            Text(
                text = stringResource(R.string.available_on_github),
                fontSize = 18.sp,
                color = colors.background,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }

        Spacer(Modifier.height(8.dp))
    }
}

private fun Context.openMyGitHub() = startActivity(
    Intent(
        Intent.ACTION_VIEW,
        Uri.parse("https://github.com/dinaraparanid")
    )
)

private fun Context.openProjectGitHub() = startActivity(
    Intent(
        Intent.ACTION_VIEW,
        Uri.parse("https://github.com/dinaraparanid/Star-Wars-Travel")
    )
)