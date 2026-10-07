package com.example.pvd_caixamei.ui.produtos

import android.content.Context
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.addPathNodes
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pvd_caixamei.R
import com.example.pvd_caixamei.data.ProdutoEntity
import com.example.pvd_caixamei.ui.produtos.componentes.NewProductDialog
import com.example.pvd_caixamei.ui.produtos.componentes.ProdutosCard
import com.example.pvd_caixamei.ui.theme.PinkDashboard

@Composable
fun ProdutosUI(
    produtosViewModel: ProdutosViewModel,
    produtosUiState: State<ProdutosUiState>,
    context: Context,
    modifier: Modifier
) {

    val produtosList = produtosUiState.value.produtoList
    val productCount = produtosUiState.value.productNumber

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
                    text = stringResource(R.string.Produtos_produtos),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                )

                Text(
                    text = stringResource(R.string._5_produtos_em_estoque, productCount),
                    fontSize = 11.sp,
                    color = Color.Gray,
                    modifier = Modifier
                )

            }

            // Botão Adicionar Produto
            Box(
                modifier = Modifier
                    .size(46.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(PinkDashboard)
                    .clickable { produtosViewModel.openAddProduct() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.produtos_adicionar_ao_produto),
                    tint = Color.White
                )
            }



        }

        // Lista de produtos no estoque
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 500.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ){
            items(produtosList) { produto ->
                ProdutosCard(
                    image = null,
                    productName = produto.nome,
                    value = produto.price,
                    quantity = produto.estoque,
                    category = produto.categoria,
                    deleteProduct = {
                        produtosViewModel.deleteProduto(produto)
                        produtosViewModel.loadAllProducts()
                    },
                    modifier = Modifier
                )
            }
        }

    }

    // Caixas de Diálogos
    if (produtosUiState.value.openAddProductDialog) {
        NewProductDialog(
            isValid = produtosUiState.value.isValid,
            onDismissRequest = { produtosViewModel.closeAddProduct() },
            productName = produtosUiState.value.productName,
            categoryName = produtosUiState.value.categoryName,
            productPrice = produtosUiState.value.productPrice,
            quantity = produtosUiState.value.productQuantity,
            onCategoryChange = { categoryName -> produtosViewModel.onProductCategoryChange(categoryName) },
            onProductNameChange = { productName -> produtosViewModel.onProductNameChange(productName) },
            onProductPriceChange = { productPrice -> produtosViewModel.onProductPrice(productPrice) },
            onQuantityChange = { productQuantity -> produtosViewModel.onProducQuantityChange(productQuantity) },
            showValidationErros = { produtosViewModel.showValidationErros(context) },
            addNewProduct = {
                produtosViewModel.addProduto(
                    ProdutoEntity(
                        nome = produtosUiState.value.productName,
                        categoria = produtosUiState.value.categoryName,
                        price = produtosUiState.value.productPrice.toDoubleOrNull() ?: 0.0,
                        estoque = produtosUiState.value.productQuantity.toIntOrNull() ?: 0
                    ),
                    context = context
                )
            },
            productNameError = produtosUiState.value.productNameError,
            categoryNameError = produtosUiState.value.categoryNameError,
            productPriceError = produtosUiState.value.productPriceError,
            productQuantityError = produtosUiState.value.productQuantityError,
            modifier = Modifier
        )
    }

}

@Preview
@Composable
fun ProdutosUIPreview() {

    val produtosViewModel: ProdutosViewModel = viewModel()
    val produtosUiState = produtosViewModel.produtosUiState.collectAsState()
    val context = LocalContext.current

    ProdutosUI(
        produtosViewModel,
        produtosUiState,
        context,
        Modifier
    )

}