package com.paranid5.star_wars_travel.planets.presentation.planets

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.paranid5.star_wars_travel.resources.R
import com.paranid5.star_wars_travel.resources.ui.LocalAppColors
import com.paranid5.star_wars_travel.resources.ui.StarWarsYellow

@Composable
fun PlanetsSearchBar(
    viewModel: PlanetsViewModel,
    modifier: Modifier = Modifier
) {
    val colors = LocalAppColors.current

    val searchText by viewModel
        .searchTextState
        .collectAsState()

    BasicTextField(
        value = searchText,
        singleLine = true,
        onValueChange = viewModel::setSearchText,
        textStyle = TextStyle(
            color = colors.onBackground,
            fontSize = 14.sp
        ),
        modifier = modifier
            .clip(RoundedCornerShape(24.dp))
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.15F),
                shape = RoundedCornerShape(24.dp)
            ),
        decorationBox = {
            SearchDecorBox(
                innerTextField = it,
                viewModel = viewModel
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SearchDecorBox(
    innerTextField: @Composable () -> Unit,
    viewModel: PlanetsViewModel
) {
    val colors = LocalAppColors.current

    val searchText by viewModel
        .searchTextState
        .collectAsState()

    val interactionSrc = remember { MutableInteractionSource() }
    val isFocused by interactionSrc.collectIsFocusedAsState()

    TextFieldDefaults.DecorationBox(
        value = searchText,
        innerTextField = innerTextField,
        enabled = true,
        singleLine = true,
        visualTransformation = VisualTransformation.None,
        interactionSource = interactionSrc,
        prefix = { SearchIcon() },
        suffix = {
            CancelIcon(
                viewModel = viewModel,
                isFocused = isFocused,
                modifier = Modifier.size(20.dp)
            )
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = colors.onBackground,
            unfocusedTextColor = colors.onBackground,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedContainerColor = StarWarsYellow.copy(alpha = 0.1F),
            unfocusedContainerColor = StarWarsYellow.copy(alpha = 0.1F)
        ),
        placeholder = { Placeholder() }
    )
}

@Composable
private fun SearchIcon(modifier: Modifier = Modifier) {
    val colors = LocalAppColors.current

    Row(modifier) {
        Icon(
            painter = painterResource(R.drawable.search),
            contentDescription = stringResource(R.string.search),
            tint = colors.onBackground,
            modifier = Modifier.size(20.dp)
        )

        Spacer(Modifier.width(8.dp))
    }
}

@Composable
private fun CancelIcon(
    viewModel: PlanetsViewModel,
    isFocused: Boolean,
    modifier: Modifier = Modifier
) {
    val colors = LocalAppColors.current

    val searchText by viewModel
        .searchTextState
        .collectAsState()

    val isIconActive by remember(searchText, isFocused) {
        derivedStateOf { searchText.isNotEmpty() || isFocused }
    }

    val iconColor by remember(isIconActive) {
        derivedStateOf { if (isIconActive) colors.onBackground else Color.Transparent }
    }

    Icon(
        painter = painterResource(R.drawable.cross),
        contentDescription = stringResource(R.string.cancel_search),
        tint = iconColor,
        modifier = modifier
            .clickable(enabled = isIconActive) {
                viewModel.setSearchText("")
            }
    )
}

@Composable
private fun Placeholder(modifier: Modifier = Modifier) =
    Text(
        text = stringResource(R.string.enter_planet),
        fontSize = 14.sp,
        modifier = modifier
    )