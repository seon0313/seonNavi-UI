package com.seon06.seon_navi

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun telephoneView(modifier: Modifier){
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text("telephone")
    }
}