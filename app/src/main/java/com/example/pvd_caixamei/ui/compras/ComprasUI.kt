package com.example.pvd_caixamei.ui.compras

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.pvd_caixamei.ui.compras.componentes.CarrinhoDeComprasDialog
import com.example.pvd_caixamei.ui.produtos.ProdutosUiState
import com.example.pvd_caixamei.ui.theme.PinkDashboard
import com.example.pvd_caixamei.ui.vendas.componentes.AdicionarProdutoDialog

import com.example.pvd_caixamei.ui.compras.componentes.ComprasRecentesCard
import com.example.pvd_caixamei.ui.vendas.componentes.VendasCard
import kotlinx.coroutines.delay

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ComprasUI(
    comprasViewModel: ComprasViewModel,
    comprasUiState: State<ComprasUiState>,
    produtosUiState: State<ProdutosUiState>,
    context: Context,
    modifier: Modifier
) {

    var showContent by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        delay(100)
        showContent = true
    }

    val produtoList = comprasUiState.value.produtoList
    val comprasList = comprasUiState.value.comprasList
    val comprasListOrd = comprasList.sortedByDescending { it.dateTime }

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
                    text = "Nova Compra",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                )

                Text(
                    text = "Registre suas compras",
                    fontSize = 11.sp,
                    color = Color.Gray,
                    modifier = Modifier
                )

            }

            // Botão Adicionar Itens
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .width(100.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(PinkDashboard)
                    .clickable {
                        comprasViewModel.openCarrinhoDeVendasDialog(
                            produtoList = produtosUiState.value.produtoList
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
                        imageVector = Icons.Outlined.ShoppingBag,
                        contentDescription = "Realizar comprar",
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

        /*Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(PinkDashboard)
                .height(50.dp)
                .clickable {  }
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    tint = Color.White,
                    contentDescription = "Adicionar item",
                    modifier = Modifier
                        .size(20.dp)
                )
                
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Adicionar item",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }

        }*/

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Compras Recentes",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Histórico de Compras
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 500.dp,),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            items(comprasListOrd) { compra ->

                AnimatedVisibility(
                    visible = showContent,
                    enter = fadeIn() + slideInVertically(
                        initialOffsetY = { 30 }
                    )
                ) {
                    ComprasRecentesCard(
                        dateTime = compra.dateTime,
                        value = compra.price,
                        itemName = compra.nomeCompra,
                        unit = compra.quantity,
                        modifier = Modifier
                    )
                }
            }
        }

    }

    // Caixas de Diálogos
    if (comprasUiState.value.openCarrinhaDeCompraDialog) {
        CarrinhoDeComprasDialog(
            produtoList = produtoList,
            shoppingCart =  comprasUiState.value.shoppingCarList,
            onDismissRequest = { comprasViewModel.closeCarrinhoDeVendasDialog() },
            onOpenProductSelector = { comprasViewModel.openProductSelectorDialog() },
            onAddProduct = { produto ->
                comprasViewModel.addProductToCart(
                    produto
                )

                comprasViewModel.closeProductSelectorDialog()
            },
            onIncreaseProduct = { produtoId ->
                comprasViewModel.increaseProductQuantity(
                    produtoId
                )
            },
            onDecreaseProduct = { produtoId ->
                comprasViewModel.decreaseProductQuantity(
                    produtoId
                )
            },
            onRemoveProduct = { produtoId ->
                comprasViewModel.removeProductFromCart(
                    produtoId
                )
            },
            onUnitPriceChange = { produtoId, price ->
                comprasViewModel.updatePurchaseUnitPrice(
                    produtoId = produtoId,
                    price = price
                )
            },
            isValid = comprasUiState.value.isValid,
            totalValue = comprasUiState.value.totalValue,
            showValidationErros = { comprasViewModel.showValidationErros(context) },
            finishBuy = { comprasViewModel.finishBuy(context) },
            modifier = Modifier
        )
    }

    if (comprasUiState.value.openProductSelectorDialog) {

        AdicionarProdutoDialog(

            produtoList = produtosUiState.value.produtoList,

            onProductSelected = { produto ->

                comprasViewModel.addProductToCart(
                    produto
                )

                comprasViewModel.closeProductSelectorDialog()
            },

            onDismissRequest = {
                comprasViewModel.closeProductSelectorDialog()
            }
        )
    }

}

@Preview
@Composable
fun VendasUIPreview() {

    /*val comprasViewModel: comprasViewModel = viewModel()
    val comprasUiState = comprasViewModel.comprasUiState.collectAsState()
    val context = LocalContext.current

   *//* ComprasUI(
        comprasViewModel,
        comprasUiState,
        context,
        Modifier
    )*/

}