package com.seon06.seon_navi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.stringResource
import seonnavi.composeapp.generated.resources.Res
import seonnavi.composeapp.generated.resources.camera
import seonnavi.composeapp.generated.resources.dashboard
import seonnavi.composeapp.generated.resources.music
import seonnavi.composeapp.generated.resources.navigation
import seonnavi.composeapp.generated.resources.setting
import seonnavi.composeapp.generated.resources.telephone

data class Navitems (
    val icon: String = "",
    val label: String = "",
    val route: String = "mainmenuView",
)

@Composable
fun mainmenuView(modifier: Modifier, click: (Navitems) -> Unit) {
    val navItems = listOf<Navitems>(
        Navitems(label =stringResource(Res.string.telephone), route = "telephoneView"),
        Navitems(label =stringResource(Res.string.navigation), route = "navigationView"),
        Navitems(label =stringResource(Res.string.camera), route = "cameraView"),
        Navitems(label =stringResource(Res.string.dashboard), route = "dashboardView"),
        Navitems(label =stringResource(Res.string.setting), route = "settingView"),
        Navitems(label =stringResource(Res.string.music), route = "musicView"),
    )
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Adaptive(minSize = 150.dp),
        contentPadding = PaddingValues(50.dp),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        items(navItems) {
            Card(
                modifier = Modifier.aspectRatio(1/1f),
                onClick = { click(it) }
            ) {
                Text(it.label, textAlign = TextAlign.Center, modifier= Modifier.fillMaxWidth())
            }
        }
    }
}