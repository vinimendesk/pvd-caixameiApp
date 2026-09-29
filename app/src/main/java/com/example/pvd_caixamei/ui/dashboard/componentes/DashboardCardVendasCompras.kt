package com.example.pvd_caixamei.ui.dashboard.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DataExploration
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pvd_caixamei.R
import com.example.pvd_caixamei.ui.theme.GreenDashboard
import com.example.pvd_caixamei.ui.theme.GreenDashboardBox
import com.example.pvd_caixamei.ui.theme.RedDashboard
import com.example.pvd_caixamei.ui.theme.RedDashboardBox

@Composable
fun DashboardCardVendasCompras(
    type: Int,
    value: Double,
    modifier: Modifier
) {

    OutlinedCard(
        modifier = Modifier
            .width(170.dp)
            .height(50.dp)
    ) {

        // Icon + ProductName
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxHeight()
        ) {

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 14.dp, end = 12.dp)
                    .size(35.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(if (type == 0) GreenDashboardBox else RedDashboardBox)
            ) {

                Icon(
                    imageVector = Icons.Outlined.DataExploration,
                    contentDescription = if(type == 0) stringResource(R.string.vendas) else stringResource(R.string.compras) ,
                    tint = if(type == 0) GreenDashboard else RedDashboard,
                    modifier = Modifier
                        .size(25.dp)
                )

            }

            // Sell Name + LocalDateTime
            Column {

                // Vendas / Compras
                Text(
                    text = if (type == 0) stringResource(R.string.vendas) else stringResource(R.string.compras),
                    fontSize = 10.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(bottom = 1.dp)
                )

                // value
                Text(
                    text = stringResource(R.string.produtosCard_value, value),
                    fontSize = 14.sp,
                    color = if(type == 0) GreenDashboard else RedDashboard,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(end = 4.dp)
                )
            }

        }

    }

}

@Preview
@Composable
fun DashboardCardVendasComprasPreview() {

    Column {
        DashboardCardVendasCompras(
            type = 0,
            value = 2.50,
            modifier = Modifier
        )
        DashboardCardVendasCompras(
            type = 1,
            value = 800.00,
            modifier = Modifier
        )
    }

}