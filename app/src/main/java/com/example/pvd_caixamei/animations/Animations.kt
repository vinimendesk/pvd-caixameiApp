package com.example.pvd_caixamei.animations

import androidx.compose.ui.graphics.Color
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.keyframes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import com.example.pvd_caixamei.ui.theme.PinkGray

// Animação de shake horizontal
@Composable
fun shakeAnimation(trigger: Boolean, trigger2: Boolean?): Float {
    val offsetX = remember { Animatable(0f) }

    if (trigger || trigger2 == true) {
        LaunchedEffect(Unit) {
            offsetX.animateTo(
                targetValue = 0f,
                animationSpec = keyframes {
                    durationMillis = 400
                    -10f at 0
                    10f at 50
                    -10f at 100
                    10f at 150
                    -10f at 200
                    6f at 250
                    -6f at 300
                    3f at 350
                    0f at 400
                }
            )
        }
    }

    return offsetX.value
}

// troca de cores Red / White. TextField
@Composable
fun errorContainerColor(showError: Boolean, showError2: Boolean?): Color {
    return animateColorAsState(
        targetValue = if (showError || showError2 == true) Color.Red else Color.White,
        label = "containerColor"
    ).value
}

// troca de cores texto White / Cinza
@Composable
fun errorTextColor(showError: Boolean, showError2: Boolean?): Color {
    return animateColorAsState(
        targetValue = if (showError || showError2 == true) Color.White else Color.Gray,
        label = "textColor"
    ).value
}