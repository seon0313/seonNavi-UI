package com.seon06.seon_navi

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            var selectedItem by remember { mutableStateOf(Navitems()) }
            Column(modifier = Modifier.padding(innerPadding).fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally) {
                if (selectedItem.route.isNotEmpty()) {
                    when (selectedItem.route) {
                        "telephoneView" -> telephoneView(Modifier.weight(1f))
                        "navigationView" -> navigationView(Modifier.weight(1f))
                        "cameraView" -> cameraView(Modifier.weight(1f))
                        "dashboardView" -> dashboardView(Modifier.weight(1f))
                        "settingView" -> settingView(Modifier.weight(1f))
                        "musicView" -> musicView(Modifier.weight(1f))
                        "mainmenuView" -> mainmenuView(modifier = Modifier.weight(1f), { selectedItem = it })
                    }
                }
                bottomNavigation(Modifier.fillMaxWidth().background(Color.DarkGray), {selectedItem = Navitems()})
            }
        }
    }
}