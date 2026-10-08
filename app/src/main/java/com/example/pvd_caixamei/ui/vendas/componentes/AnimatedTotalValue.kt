package com.example.pvd_caixamei.ui.vendas.componentes

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.pvd_caixamei.ui.theme.PinkDashboard

@Composable
fun AnimatedTotalValue(
    value: Double,
    modifier: Modifier = Modifier
) {
    val animatedValue by animateFloatAsState(
        targetValue = value.toFloat(),
        animationSpec = tween(
            durationMillis = 400,
            easing = FastOutSlowInEasing
        ),
        label = "totalValueAnimation"
    )

    Text(
        text = "R$ %.2f".format(animatedValue),
        modifier = modifier,
        fontSize = 22.sp,
        color = PinkDashboard,
        fontWeight = FontWeight.Bold
    )
}