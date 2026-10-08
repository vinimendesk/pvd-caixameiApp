package com.example.pvd_caixamei.ui.vendas

import com.example.pvd_caixamei.ui.produtos.ProdutosUiState

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pvd_caixamei.data.VendasEntity
import com.example.pvd_caixamei.ui.produtos.componentes.ProdutosCard
import com.example.pvd_caixamei.ui.theme.PinkDashboard
import com.example.pvd_caixamei.ui.vendas.componentes.AdicionarProdutoDialog
import com.example.pvd_caixamei.ui.vendas.componentes.CarrinhoDeVendaDialog
import com.example.pvd_caixamei.ui.vendas.componentes.VendasCard
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun VendasUI(
    vendasViewModel: VendasViewModel,
    vendasUiState: State<VendasUiState>,
    produtosUiState: State<ProdutosUiState>,
    context: Context,
    modifier: Modifier
) {

    val produtoList = vendasUiState.value.produtoList
    val vendasList = vendasUiState.value.vendasList
    val vendasListOrd = vendasList.sortedByDescending { it.dateTime }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
    ) {

        // Produtos + Botão de Adicionar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 28.dp)
        ) {

            // Produtos + 5 produtos em estoque
            Column {

                Text(
                    text = "Nova Venda",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                )

                Text(
                    text = "Selecione os produtos",
                    fontSize = 11.sp,
                    color = Color.Gray,
                    modifier = Modifier
                )

            }

            // Botão Adicionar Produto
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .width(105.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(PinkDashboard)
                    .clickable {
                        /*vendasViewModel.calculateTotalValue()*/
                        vendasViewModel.openCarrinhoDeVendasDialog(
                            produtosUiState.value.produtoList
                        )
                               },
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ShoppingCart,
                        contentDescription = "Realizar venda",
                        tint = Color.White,
                        modifier = Modifier
                            .size(15.dp)
                    )

                    Text(
                        text = "Carrinho",
                        color = Color.White,
                        fontSize = 14.sp,
                    )
                }
            }



        }

        // Histórico de Vendas
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 500.dp,),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            items(vendasListOrd) { venda ->
                VendasCard(
                    image = null,
                    productName = venda.nomeVenda,
                    quantity = venda.quantity,
                    value = venda.valorVenda,
                    modifier = Modifier
                )
            }
        }

    }

    // Caixas de Diálogos
    if (vendasUiState.value.openCarrinhaDeVendaDialog) {
        CarrinhoDeVendaDialog(
            produtoList = produtoList,
            shoppingCart =  vendasUiState.value.shoppingCarList,
            onDismissRequest = { vendasViewModel.closeCarrinhoDeVendasDialog() },
            onOpenProductSelector = { vendasViewModel.openProductSelectorDialog() },
            onAddProduct = { produto ->
                vendasViewModel.addProductToCart(
                    produto
                )

                vendasViewModel.closeProductSelectorDialog()
            },
            onIncreaseProduct = { produtoId ->
                vendasViewModel.increaseProductQuantity(
                    produtoId
                )
            },
            onDecreaseProduct = { produtoId ->
                vendasViewModel.decreaseProductQuantity(
                    produtoId
                )
            },
            onRemoveProduct = { produtoId ->
                vendasViewModel.removeProductFromCart(
                    produtoId
                )
            },
            isValid = vendasUiState.value.isValid,
            totalValue = vendasUiState.value.totalValue,
            showValidationErros = { vendasViewModel.showValidationErros(context) },
            finishSale = { vendasViewModel.finishSale(context) },
            modifier = Modifier
        )
    }

    if (vendasUiState.value.openProductSelectorDialog) {

        AdicionarProdutoDialog(

            produtoList = produtosUiState.value.produtoList,

            onProductSelected = { produto ->

                vendasViewModel.addProductToCart(
                    produto
                )

                vendasViewModel.closeProductSelectorDialog()
            },

            onDismissRequest = {
                vendasViewModel.closeProductSelectorDialog()
            }
        )
    }

}

@Preview
@Composable
fun VendasUIPreview() {

    val vendasViewModel: VendasViewModel = viewModel()
    val vendasUiState = vendasViewModel.vendasUiState.collectAsState()
    val context = LocalContext.current

    /*VendasUI(
        vendasViewModel,
        vendasUiState,
        context,
        Modifier
    )*/

}