package com.example.pvd_caixamei.ui.compras.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pvd_caixamei.data.ProdutoEntity

@Composable
fun AdicionarProdutoCompraDialog(
    produtoList: List<ProdutoEntity>,
    onProductSelected: (ProdutoEntity) -> Unit,
    onDismissRequest: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,

        title = {
            Text(
                text = "Adicionar produto"
            )
        },

        text = {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {

                items(produtoList) { produto ->

                    OutlinedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                /*
                                 * Seleciona o produto e devolve
                                 * para o carrinho de compras.
                                 *
                                 * Diferentemente da venda,
                                 * produtos com estoque 0 também
                                 * podem ser selecionados.
                                 */
                                onProductSelected(produto)
                            }
                    ) {

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),

                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Column(
                                modifier = Modifier.weight(1f)
                            ) {

                                Text(
                                    text = produto.nome,
                                    fontWeight = FontWeight.Bold
                                )

                                Text(
                                    text = "Estoque: ${produto.estoque}",
                                    fontSize = 12.sp,
                                    color = Color.Gray
                                )
                            }

                            /*
                             * Aqui mostramos o preço de venda
                             * apenas como informação.
                             *
                             * Ele NÃO será utilizado como preço
                             * da compra.
                             */
                            Text(
                                text = "Venda: R$ %.2f".format(produto.price),
                                fontSize = 12.sp,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }
        },

        confirmButton = {},

        dismissButton = {
            TextButton(
                onClick = onDismissRequest
            ) {
                Text("Cancelar")
            }
        }
    )
}