package com.example.pvd_caixamei.ui.vendas.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Minimize
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
import com.example.pvd_caixamei.ui.theme.PinkDashboard

@Composable
fun CarrinhoDeVendasCard(
    item: ShoppingCartItem,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onRemove: () -> Unit,
    image: ImageVector?,
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
                    .padding(start = 14.dp)
            ) {

                // Product Name + Value
                Column {

                    // Product Name
                    Text(
                        text = item.produto.nome,
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Value
                        Text(
                            text = stringResource(R.string.carrrinhoDeVendasCard_value, item.produto.price, item.produto.estoque),
                            fontSize = 12.sp,
                            color = Color.Gray,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(end = 8.dp)
                        )

                        Text(
                            text = ": R$ %.2f".format(item.totalValue),
                            fontSize = 12.sp,
                            color = PinkDashboard,
                            fontWeight = FontWeight.Bold
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

                // Menos
                IconButton(
                    onClick = { onDecrease() },
                    modifier = Modifier
                        .clip(RoundedCornerShape(25.dp))
                        .size(25.dp)
                        .background(Color.White)
                ) {
                        Icon(
                            imageVector = Icons.Default.Minimize,
                            contentDescription = "Remover um item de ${item.produto.nome} do carrinho",
                            tint = Color.Black,
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                        )

                    }

                Spacer(modifier = Modifier.width(12.dp))

                Text(
                    text = item.quantity.toString(),

                )

                Spacer(modifier = Modifier.width(12.dp))

                // Mais
                IconButton(
                    onClick = { onIncrease() },
                    modifier = Modifier
                        .clip(RoundedCornerShape(25.dp))
                        .size(25.dp)
                        .background(Color.White)
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Remover um item de ${item.produto.nome} do carrinho",
                        tint = Color.Black,
                        modifier = Modifier
                            .size(13.dp)
                    )

                }

                // Excluir
                IconButton(
                    onClick = { onRemove() },
                    modifier = Modifier
                        .clip(RoundedCornerShape(25.dp))
                        .size(25.dp)
                        .background(Color.White)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Remover um item de ${item.produto.nome} do carrinho",
                        tint = Color.Red,
                        modifier = Modifier
                            .size(13.dp)
                    )

                }

                }
            }

        }

    }


@Preview
@Composable
fun CarrinhoDeVendaCardPreview() {

    /*CarrinhoDeVendasCard(
        image = Icons.Outlined.Inventory2,
        productName = "Refrigerante 2L",
        value = 12.00,
        quantity = 50,
        category = "Bebidas",
        modifier = Modifier
    )*/

}