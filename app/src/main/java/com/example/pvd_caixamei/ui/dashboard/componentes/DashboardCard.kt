package com.example.pvd_caixamei.ui.dashboard.componentes

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.material.icons.outlined.DataExploration
import com.example.pvd_caixamei.ui.theme.GreenDashboard
import com.example.pvd_caixamei.ui.theme.GreenDashboardBox
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pvd_caixamei.R
import com.example.pvd_caixamei.ui.theme.PinkBottomNavigationBar
import com.example.pvd_caixamei.ui.theme.PinkBottomNavigationBarSelected
import com.example.pvd_caixamei.ui.theme.RedDashboard
import com.example.pvd_caixamei.ui.theme.RedDashboardBox
import java.time.LocalDate

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DashboardCard(
    sellName: String,
    type: Int,
    dateTime: LocalDateTime,
    value: Double,
    modifier: Modifier
) {

    // Formatando o LocalDateTIme
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    val date = dateTime.format(dateFormatter)
    val hour = dateTime.format(timeFormatter)

    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .height(50.dp)
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
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
                        .clip(RoundedCornerShape(12.dp))
                        .background(if(type == 0) GreenDashboardBox else RedDashboardBox)
                ) {

                    Icon(
                        imageVector = Icons.Outlined.DataExploration,
                        contentDescription = sellName,
                        tint = if(type == 0) GreenDashboard else RedDashboard,
                        modifier = Modifier
                            .size(25.dp)
                            .rotate(if (type == 0) 0f else 60f)
                    )

                }

                // Sell Name + LocalDateTime
                Column {

                    // Product Name
                    Text(
                        text = sellName,
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(bottom = 1.dp)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Date
                        Text(
                            text = date,
                            fontSize = 10.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(end = 4.dp)
                        )

                        // Time
                        Text(
                            text = hour,
                            fontSize = 10.sp,
                            color = Color.Gray,
                        )
                    }

                }

            }

            // Value
            Text(
                text = if (type == 0) stringResource(R.string.DashboardCard_venda, value)
                        else stringResource(R.string.DashboardCard_compra, value)
                ,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = if(type == 0) GreenDashboard else RedDashboard,
                modifier = Modifier
                    .padding(end = 16.dp)
            )

        }

    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun DashCardPreview() {


    Column {
        DashboardCard(
            sellName = "Refrigerante 2L",
            type = 0,
            dateTime = LocalDateTime.of(2026, 3, 12, 14, 30),
            value = 2.50,
            modifier = Modifier
        )

        DashboardCard(
            sellName = "Caixa de Skol",
            type = 1,
            dateTime = LocalDateTime.of(2026, 3, 12, 18, 22),
            value = 8000.00,
            modifier = Modifier
        )
    }

}