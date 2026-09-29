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
import androidx.compose.material.icons.outlined.Wallet
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.example.pvd_caixamei.ui.theme.PinkBottomNavigationBar
import com.example.pvd_caixamei.ui.theme.PinkBottomNavigationBarSelected
import com.example.pvd_caixamei.ui.theme.PinkDashboard
import com.example.pvd_caixamei.ui.theme.RedDashboard
import com.example.pvd_caixamei.ui.theme.RedDashboardBox


@Composable
fun DashboardCardBalanco(
    value: Double,
    modifier: Modifier
) {

    Card(
        colors = CardDefaults.cardColors(containerColor = PinkDashboard),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 12.dp)
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
                    .background(PinkDashboard)
            ) {

                Icon(
                    imageVector = Icons.Outlined.Wallet,
                    contentDescription = stringResource(R.string.Dashboard_balan_o_do_m_s),
                    tint = Color.White,
                    modifier = Modifier
                        .size(25.dp)
                )

            }

            // Sell Name + LocalDateTime
            Column {

                // Vendas / Compras
                Text(
                    text = stringResource(R.string.Dashboard_balan_o_do_m_s),
                    fontSize = 12.sp,
                    color = Color.White,
                    modifier = Modifier
                        .padding(bottom = 1.dp)
                )

                // value
                Text(
                    text = stringResource(R.string.produtosCard_value, value),
                    fontSize = 16.sp,
                    color = Color.White,
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
fun DashboardCardBalancoPreview(){

    DashboardCardBalanco(
        value = -797.50,
        modifier = Modifier
    )

}