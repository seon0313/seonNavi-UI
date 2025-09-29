package com.seon06.seon_navi

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberWebViewState

@Composable
fun navigationView(modifier: Modifier = Modifier) {
    // 1. URL 로딩 충돌을 막기 위해 초기 URL을 null로 설정합니다. (★★★★★ 중요)
    val state = rememberWebViewState("http://localhost:5000")
    var openLeftSide by remember { mutableStateOf(true) }
    var openKeyboard by remember { mutableStateOf(true) }
    var leftSideWidth_var by remember { mutableStateOf(200.dp) }
    val leftSideWidth by animateDpAsState(leftSideWidth_var, tween(200))

    LaunchedEffect(openLeftSide){
        if (!openLeftSide){
            leftSideWidth_var = 50.dp
        } else {
            leftSideWidth_var = 200.dp
        }
    }

    Column(
        modifier = modifier
    ) {
        Row(
            modifier= Modifier.weight(1f)
        ) {
            navigationSideLeftView(modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                .fillMaxHeight()
                .width(leftSideWidth)
            , opened = openLeftSide, {
                openLeftSide = !openLeftSide
            })

            Row(
                modifier = Modifier.weight(1f)
            ) {
                WebView(
                    state = state,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row {
                Text("bottomView")
            }
            if (openKeyboard){
                Keyboard(modifier = Modifier, {openKeyboard = !openKeyboard})
            }
        }
    }
}

@Composable
fun navigationSideLeftView(modifier: Modifier = Modifier, opened: Boolean, changeOpened: () -> Unit){
    Column(
        modifier = modifier
    ) {
        if (opened) {
            Row {
                Button({ changeOpened() }, contentPadding = PaddingValues(0.dp), modifier = Modifier
                    .width(50.dp).height(40.dp)) {
                    Text(">")
                }
                BasicTextField("", {}, modifier= Modifier.weight(1f)
                    .height(40.dp)
                    .clip(RoundedCornerShape(5.dp))
                    .background(MaterialTheme.colorScheme.tertiaryContainer),
                    maxLines = 1) { innerTextField ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(5.dp,0.dp,0.dp,0.dp)
                    ) {
                        innerTextField()
                    }
                }
            }
        } else {
            Button({ changeOpened() }, contentPadding = PaddingValues(0.dp), modifier = Modifier
                .width(50.dp).height(40.dp)) {
                Text(">")
            }
        }
    }
}