package com.example.pvd_caixamei.ui.vendas

import androidx.compose.runtime.MutableState
import com.example.pvd_caixamei.data.ComprasEntity
import com.example.pvd_caixamei.data.ProdutoEntity
import com.example.pvd_caixamei.data.VendasEntity
import com.example.pvd_caixamei.ui.vendas.componentes.ShoppingCartItem

data class VendasUiState (

    val produtoList: List<ProdutoEntity> = listOf(),
    val vendasList: List<VendasEntity> = listOf(),
    val shoppingCarList: List<ShoppingCartItem> = emptyList(),
    val openCarrinhaDeVendaDialog: Boolean = false, // Verifica se a caixa de diálogo para carrinho de venda está anerta
    val openProductSelectorDialog : Boolean = false,
    val showErros: Boolean = false // Diz se pode mostrar os erros.

    ) {

        // valor total no carrinho de vendas.
        val totalValue: Double
            get() = shoppingCarList.sumOf { it.totalValue }

        // valor total de todas as vendas feitas.
        val totalValueAllVendas: Double
            get() = vendasList.sumOf { it.valorVenda }

        // Verifica se o nome do produto está vazio.
        val shoppingCarListError: Boolean get() = showErros && shoppingCarList.isEmpty()
        val finishSaleErrorDialog: Boolean get() = shoppingCarListError

        // Diz se é possível adicionar o produto.
        val isValid: Boolean get() = shoppingCarList.isNotEmpty()

    }