package com.seon06.seon_navi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun bottomNavigation(modifier: Modifier, onClick: () -> Unit){
    Row(
        modifier = modifier,
    ) {
        Button(onClick = onClick,){
            Column {
                Text("Back")
            }
        }
    }
}