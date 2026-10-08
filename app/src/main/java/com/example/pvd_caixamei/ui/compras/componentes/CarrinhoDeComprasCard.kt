package com.example.pvd_caixamei.ui.compras.componentes

import com.example.pvd_caixamei.ui.vendas.componentes.ShoppingCartItem

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Minimize
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pvd_caixamei.R
import com.example.pvd_caixamei.ui.theme.PinkBottomNavigationBar
import com.example.pvd_caixamei.ui.theme.PinkBottomNavigationBarSelected
import com.example.pvd_caixamei.ui.theme.PinkDashboard

@Composable
fun CarrinhoDeComprasCard(
    item: ShoppingCartCompraItem,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    onRemove: () -> Unit,
    onUnitPriceChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .padding(vertical = 4.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = item.produto.nome,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )

                IconButton(
                    onClick = onRemove
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Remover produto",
                        tint = Color.Red
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            /*
             * Campo onde o usuário informa quanto pagou
             * por UMA unidade do produto.
             */
            OutlinedTextField(
                value = item.unitPrice,

                onValueChange = { value ->
                    onUnitPriceChange(value)
                },

                label = {
                    Text("Preço de compra unitário")
                },

                placeholder = {
                    Text("0.00")
                },

                singleLine = true,

                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),

                modifier = Modifier.fillMaxWidth()
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {

                /*
                 * Controle da quantidade.
                 */
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    IconButton(
                        onClick = onDecrease
                    ) {
                        Icon(
                            imageVector = Icons.Default.Minimize,
                            contentDescription = "Diminuir quantidade"
                        )
                    }

                    Text(
                        text = item.quantity.toString(),
                        fontWeight = FontWeight.Bold
                    )

                    IconButton(
                        onClick = onIncrease
                    ) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Aumentar quantidade"
                        )
                    }
                }

                /*
                 * Mostra o total deste produto.
                 *
                 * Exemplo:
                 * R$ 7,00 × 50 = R$ 350,00
                 */
                Text(
                    text = "Total: R$ %.2f".format(item.totalValue),
                    fontWeight = FontWeight.Bold,
                    color = PinkDashboard
                )
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