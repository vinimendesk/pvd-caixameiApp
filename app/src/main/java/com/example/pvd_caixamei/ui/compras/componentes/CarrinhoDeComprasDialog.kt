package com.example.pvd_caixamei.ui.compras.componentes

import com.example.pvd_caixamei.ui.vendas.componentes.CarrinhoDeVendasCard
import com.example.pvd_caixamei.ui.vendas.componentes.ShoppingCartItem

import android.util.SparseArray
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.pvd_caixamei.R
import com.example.pvd_caixamei.animations.errorContainerColor
import com.example.pvd_caixamei.animations.errorTextColor
import com.example.pvd_caixamei.animations.shakeAnimation
import com.example.pvd_caixamei.data.ProdutoEntity
import com.example.pvd_caixamei.ui.theme.PinkDashboard

@Composable
fun CarrinhoDeComprasDialog(
    produtoList: List<ProdutoEntity>,
    shoppingCart: List<ShoppingCartCompraItem>,
    onAddProduct: (ProdutoEntity) -> Unit,
    onIncreaseProduct: (Int) -> Unit,
    onDecreaseProduct: (Int) -> Unit,
    onRemoveProduct: (Int) -> Unit,
    onDismissRequest: () -> Unit,
    onUnitPriceChange: (produtoId: Int, price: String) -> Unit,
    isValid: Boolean,
    onOpenProductSelector: () -> Unit,
    showValidationErros: () -> Unit,
    totalValue: Double,
    finishBuy: () -> Unit,
    modifier: Modifier
) {

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(dismissOnClickOutside = false)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .padding(horizontal = 12.dp)
        ) {

            // Novo Produto + Botão de Fechar
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp, bottom = 16.dp)
            ) {

                Text(
                    text = "Carrinho de Compra",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
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

            Spacer(
                modifier = Modifier.height(12.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 300.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(
                    items = shoppingCart,
                    key = {
                        it.produto.produtoId
                    }
                ) { item ->
                    CarrinhoDeComprasCard(
                        item = item,
                        onIncrease = {
                            onIncreaseProduct(
                                item.produto.produtoId
                            )
                        },
                        onDecrease = {
                            onDecreaseProduct(
                                item.produto.produtoId
                            )
                        },
                        onRemove = {
                            onRemoveProduct(
                                item.produto.produtoId
                            )
                        },
                        onUnitPriceChange = { price ->
                                onUnitPriceChange(
                                    item.produto.produtoId,
                                    price
                                )
                        },
                        modifier = Modifier
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Valor Total + Botão Finalizar Venda
            Row(
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Total",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.Bottom)
                )
                Text(
                    text = stringResource(R.string.TotalValueVendasDialog, totalValue),
                    fontSize = 22.sp,
                    color = PinkDashboard,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // BOTÃO ADICIONAR PRODUTO
            // =========================================================

            Button(
                onClick = {
                    onOpenProductSelector()
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = PinkDashboard
                ),
                modifier = Modifier.fillMaxWidth()
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Adicionar produto ao carrinho"
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Text(
                    text = "Adicionar produto"
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
            11
            // Button "Adicionar Produto"
            Button(
                onClick = {

                    if (!isValid) {
                        showValidationErros()
                    } else {
                        finishBuy()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = PinkDashboard),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Finalizar Compra",
                    color = Color.White
                )
            }


        }

    }
}

@Preview
@Composable
fun NewProductDialogPreview() {

    /*CarrinhoDeVendaDialog(
        produtoList = listOf(),
        onDismissRequest = {  },
        isValid = true,
        showValidationErros = {  },
        totalValue = 0.0,
        finishSale = {  },
        modifier = Modifier
    )*/

}