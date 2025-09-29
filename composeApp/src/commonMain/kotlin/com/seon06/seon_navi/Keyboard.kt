package com.seon06.seon_navi

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun Keyboard(modifier: Modifier = Modifier, close: () -> Unit){
    var shift by remember { mutableStateOf(false) }
    var special by remember { mutableStateOf(false) }
    var language by remember { mutableStateOf(0) }

    LaunchedEffect(language){
        shift = false
        if (special) special = false
        if (language > 1) language = 0
    }

    if (special){
        specialKeboardLayout(modifier = modifier, shift, {shift = it}, close, {special = !special}, { language += 1 })
    } else {
        when (language) {
            0 -> englishKeboardLayout(modifier = modifier, shift, {shift = it}, close, {special = !special}, { language += 1 })
            1 -> koreanKeboardLayout(modifier = modifier, shift, {shift = it}, close, {special = !special}, { language -= 1 })
        }
    }
}

@Composable
fun koreanKeboardLayout(modifier: Modifier = Modifier,
                        shift: Boolean,
                        changeShift: (Boolean) -> Unit,
                        close: () -> Unit,
                        changeSpecial: () -> Unit,
                        changeLanguage: () -> Unit) {
    var width by remember { mutableStateOf(0) }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .onGloballyPositioned {
                width = it.size.width
            }
    ) {
        val density = LocalDensity.current
        val pixels = width/10
        val dpValue = with(density) { pixels.toDp() }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            items(listOf("1","2","3","4","5","6","7","8","9","0")) {
                Button({}, modifier = Modifier.width(dpValue).height(30.dp),
                    contentPadding = PaddingValues(0.dp)){
                    Text(it)
                }
            }
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            items(if (!shift) listOf("ㅂ", "ㅈ", "ㄷ", "ㄱ", "ㅅ", "ㅛ", "ㅕ", "ㅑ", "ㅐ", "ㅔ")
            else listOf("ㅃ", "ㅉ", "ㄸ", "ㄲ", "ㅆ", "ㅛ", "ㅕ", "ㅑ", "ㅒ", "ㅖ")) {
                Button({}, modifier = Modifier.width(dpValue), contentPadding = PaddingValues(0.dp)){
                    Text(if (shift) it.uppercase() else it)
                }
            }
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            items(listOf("ㅁ", "ㄴ", "ㅇ", "ㄹ", "ㅎ", "ㅗ", "ㅓ", "ㅏ", "ㅣ")) {
                Button({}, modifier = Modifier.width(dpValue), contentPadding = PaddingValues(0.dp)){
                    Text(if (shift) it.uppercase() else it)
                }
            }
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            items(listOf("Shift", "ㅋ", "ㅌ", "ㅊ", "ㅍ", "ㅠ", "ㅜ", "ㅡ", "Close")) {
                var modifier_ = Modifier.width(dpValue)
                if (it == "Shift" || it == "Close") modifier_ = Modifier.width(dpValue*1.5f)
                Button({
                    if (it == "Shift") changeShift(!shift)
                    else if (it == "Close") close()
                }, modifier = modifier_, contentPadding = PaddingValues(0.dp)){
                    if (it != "Shift" && it != "Close") Text(if (shift) it.uppercase() else it)
                    else if (it == "Shift") Text("^")
                    else if (it == "Close") Text("C")
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button({changeSpecial()}, modifier=Modifier.width(dpValue*1.25f), contentPadding = PaddingValues(0.dp)){
                Text("!#1", maxLines = 1, overflow = TextOverflow.Visible)
            }
            Button({ changeLanguage() }, modifier=Modifier.width(dpValue*1.25f), contentPadding = PaddingValues(0.dp)){
                Text("한영", maxLines = 1, overflow = TextOverflow.Visible)
            }
            Button({}, modifier=Modifier.width(dpValue*5f), contentPadding = PaddingValues(0.dp)){
                Text("________", maxLines = 1, overflow = TextOverflow.Visible)
            }
            Button({}, modifier=Modifier.width(dpValue), contentPadding = PaddingValues(0.dp)){
                Text(".", maxLines = 1, overflow = TextOverflow.Clip)
            }

            Spacer(modifier=Modifier.width(dpValue*1.5f))
        }
    }
}


@Composable
fun englishKeboardLayout(modifier: Modifier = Modifier,
                         shift: Boolean,
                         changeShift: (Boolean) -> Unit,
                         close: () -> Unit,
                         changeSpecial: () -> Unit,
                         changeLanguage: () -> Unit) {
    var width by remember { mutableStateOf(0) }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .onGloballyPositioned {
                width = it.size.width
            }
    ) {
        val density = LocalDensity.current
        val pixels = width/10
        val dpValue = with(density) { pixels.toDp() }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            items(listOf("1","2","3","4","5","6","7","8","9","0")) {
                Button({}, modifier = Modifier.width(dpValue).height(30.dp),
                    contentPadding = PaddingValues(0.dp)){
                    Text(it)
                }
            }
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            items(listOf("q", "w", "e", "r", "t", "y", "u", "i", "o", "p")) {
                Button({}, modifier = Modifier.width(dpValue), contentPadding = PaddingValues(0.dp)){
                    Text(if (shift) it.uppercase() else it)
                }
            }
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            items(listOf("a", "s", "d", "f", "g", "h", "j", "k", "l")) {
                Button({}, modifier = Modifier.width(dpValue), contentPadding = PaddingValues(0.dp)){
                    Text(if (shift) it.uppercase() else it)
                }
            }
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            items(listOf("Shift", "z", "x", "c", "v", "b", "n", "m", "Close")) {
                var modifier_ = Modifier.width(dpValue)
                if (it == "Shift" || it == "Close") modifier_ = Modifier.width(dpValue*1.5f)
                Button({
                    if (it == "Shift") changeShift(!shift)
                    else if (it == "Close") close()
                }, modifier = modifier_, contentPadding = PaddingValues(0.dp)){
                    if (it != "Shift" && it != "Close") Text(if (shift) it.uppercase() else it)
                    else if (it == "Shift") Text("^")
                    else if (it == "Close") Text("C")
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button({ changeSpecial() }, modifier=Modifier.width(dpValue*1.25f), contentPadding = PaddingValues(0.dp)){
                Text("!#1", maxLines = 1, overflow = TextOverflow.Clip)
            }
            Button({ changeLanguage() }, modifier=Modifier.width(dpValue*1.25f), contentPadding = PaddingValues(0.dp)){
                Text("한영", maxLines = 1, overflow = TextOverflow.Clip)
            }
            Button({}, modifier=Modifier.width(dpValue*5f), contentPadding = PaddingValues(0.dp)){
                Text("________", maxLines = 1, overflow = TextOverflow.Clip)
            }
            Button({}, modifier=Modifier.width(dpValue), contentPadding = PaddingValues(0.dp)){
                Text(".", maxLines = 1, overflow = TextOverflow.Clip)
            }

            Spacer(modifier=Modifier.width(dpValue*1.5f))
        }
    }
}

@Composable
fun specialKeboardLayout(modifier: Modifier = Modifier,
                         shift: Boolean,
                         changeShift: (Boolean) -> Unit,
                         close: () -> Unit,
                         changeSpecial: () -> Unit,
                         changeLanguage: () -> Unit){
    var width by remember { mutableStateOf(0) }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .onGloballyPositioned {
                width = it.size.width
            }
    ) {
        val density = LocalDensity.current
        val pixels = width/10
        val dpValue = with(density) { pixels.toDp() }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            items(listOf("1","2","3","4","5","6","7","8","9","0")) {
                Button({}, modifier = Modifier.width(dpValue).height(30.dp),
                    contentPadding = PaddingValues(0.dp)){
                    Text(it)
                }
            }
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            items(listOf("+", "×", "÷", "=", "/", "_", "<", ">", "[", "]")) {
                Button({}, modifier = Modifier.width(dpValue), contentPadding = PaddingValues(0.dp)){
                    Text(if (shift) it.uppercase() else it)
                }
            }
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            items(listOf("!", "@", "#", "$", "%", "^", "&", "*", "(", ")")) {
                Button({}, modifier = Modifier.width(dpValue), contentPadding = PaddingValues(0.dp)){
                    Text(if (shift) it.uppercase() else it)
                }
            }
        }
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            items(listOf("Shift", "-", "'", "\"", ":", ";", ",", "?", "Close")) {
                var modifier_ = Modifier.width(dpValue)
                if (it == "Shift" || it == "Close") modifier_ = Modifier.width(dpValue*1.5f)
                Button({
                    if (it == "Shift") changeShift(!shift)
                    else if (it == "Close") close()
                }, modifier = modifier_, contentPadding = PaddingValues(0.dp)){
                    if (it != "Shift" && it != "Close") Text(if (shift) it.uppercase() else it)
                    else if (it == "Shift") Text("^")
                    else if (it == "Close") Text("C")
                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button({ changeSpecial() }, modifier=Modifier.width(dpValue*0.83f), contentPadding = PaddingValues(0.dp)){
                Text("!#1", maxLines = 1, overflow = TextOverflow.Clip)
            }
            Button({ changeLanguage() }, modifier=Modifier.width(dpValue*0.83f), contentPadding = PaddingValues(0.dp)){
                Text("한영", maxLines = 1, overflow = TextOverflow.Clip)
            }
            Button({}, modifier=Modifier.width(dpValue*0.83f), contentPadding = PaddingValues(0.dp)){
                Text("%", maxLines = 1, overflow = TextOverflow.Clip)
            }
            Button({}, modifier=Modifier.width(dpValue*5f), contentPadding = PaddingValues(0.dp)){
                Text("________", maxLines = 1, overflow = TextOverflow.Clip)
            }
            Button({}, modifier=Modifier.width(dpValue*0.83f), contentPadding = PaddingValues(0.dp)){
                Text(".", maxLines = 1, overflow = TextOverflow.Clip)
            }
            Spacer(modifier=Modifier.width(dpValue*1.67f))
        }
    }
}