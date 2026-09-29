package com.example.pvd_caixamei.ui.vendas

import androidx.compose.runtime.MutableState

data class VendasUiState (

    val openCarrinhaDeVendaDialog: Boolean = false, // Verifica se a caixa de diálogo para carrinho de venda está anerta
    val shoppingCarList: List<Int> = emptyList(),
    val showErros: Boolean = false // Diz se pode mostrar os erros.
    ) {

        // Verifica se o nome do produto está vazio.
        val shoppingCarListError: Boolean get() = showErros && shoppingCarList.isEmpty()
        val finishSaleErrorDialog: Boolean get() = shoppingCarListError

        // Diz se é possível adicionar o produto.
        val isValid: Boolean get() = shoppingCarList.isNotEmpty()

    }