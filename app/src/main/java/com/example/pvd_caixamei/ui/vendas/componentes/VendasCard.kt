package com.example.pvd_caixamei.ui.vendas.componentes

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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pvd_caixamei.R
import com.example.pvd_caixamei.ui.theme.PinkBottomNavigationBar
import com.example.pvd_caixamei.ui.theme.PinkBottomNavigationBarSelected

@Composable
fun VendasCard(
    image: ImageVector?,
    productName: String,
    value: Double,
    quantity: Int,
    category: String,
    modifier: Modifier
) {

    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .height(60.dp)
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
                        .size(40.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(PinkBottomNavigationBar)
                ) {

                    Icon(
                        imageVector = image ?: Icons.Outlined.Inventory2,
                        contentDescription = productName,
                        tint = PinkBottomNavigationBarSelected,
                        modifier = Modifier
                            .size(25.dp)
                    )

                }

                // Product Name + Value
                Column {

                    // Product Name
                    Text(
                        text = productName,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Value
                        Text(
                            text = stringResource(R.string.produtosCard_value, value),
                            fontSize = 12.sp,
                            color = PinkBottomNavigationBarSelected,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(end = 8.dp)
                        )

                        // /un
                        Text(
                            text = "/un",
                            fontSize = 10.sp,
                            color = Color.Gray,
                            modifier = Modifier
                                .padding(end = 8.dp)
                        )
                    }

                }

            }


            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(end = 14.dp)
            ) {
                // Texto 100 un
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .height(25.dp)
                        .background(PinkBottomNavigationBar)
                ) {

                    Text(
                        text = stringResource(R.string.produtosCard_quantity, quantity),
                        fontSize = 12.sp,
                        color = Color.Gray,
                        modifier = Modifier
                            .padding(start = 6.dp, end = 6.dp)
                            .align(Alignment.Center)
                    )

                }
            }

        }

    }

}

@Preview
@Composable
fun ProdutosCardPreview() {

    VendasCard(
        image = Icons.Outlined.Inventory2,
        productName = "Refrigerante 2L",
        value = 12.00,
        quantity = 50,
        category = "Bebidas",
        modifier = Modifier
    )

}