package com.example.pvd_caixamei.ui.vendas.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.pvd_caixamei.R
import com.example.pvd_caixamei.data.ProdutoEntity

@Composable
fun AdicionarProdutoDialog(
    produtoList: List<ProdutoEntity>,
    onProductSelected: (ProdutoEntity) -> Unit,
    onDismissRequest: () -> Unit
) {

    Dialog(
        onDismissRequest = onDismissRequest
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(Color.White)
                    .padding(20.dp)
            ) {

                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, bottom = 16.dp)
                ) {

                    Text(
                        text = "Adicionar produto",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    IconButton(
                        onClick = { onDismissRequest() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(R.string.NewProduct_fechar_adicionar_produto),
                            tint = Color.Gray
                        )
                    }

                }

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    items(produtoList) { produto ->

                        OutlinedCard(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {

                                    // Produto escolhido pelo usuário.
                                    onProductSelected(produto)
                                }
                        ) {

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(12.dp)
                            ) {

                                Column {

                                    Text(
                                        text = produto.nome,
                                        fontWeight = FontWeight.Bold
                                    )

                                    Row {

                                        Text(
                                            text = "R$ %.2f".format(produto.price),
                                            fontSize = 12.sp
                                        )

                                        Spacer(modifier = Modifier.width(8.dp))

                                        Text(
                                            text = "Estoque: ${produto.estoque}",
                                            fontSize = 11.sp
                                        )
                                    }
                                }

                                IconButton(
                                    onClick = {
                                        onProductSelected(produto)
                                    }
                                ) {

                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = "Adicionar produto"
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}