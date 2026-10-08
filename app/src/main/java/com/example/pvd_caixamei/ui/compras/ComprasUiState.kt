package com.example.pvd_caixamei.ui.compras

import com.example.pvd_caixamei.data.ComprasEntity
import com.example.pvd_caixamei.data.ProdutoEntity
import com.example.pvd_caixamei.data.VendasEntity
import com.example.pvd_caixamei.ui.compras.componentes.ShoppingCartCompraItem
import com.example.pvd_caixamei.ui.vendas.componentes.ShoppingCartItem

data class ComprasUiState(
    val produtoList: List<ProdutoEntity> = listOf(),
    val comprasList: List<ComprasEntity> = listOf(),
    val shoppingCarList: List<ShoppingCartCompraItem> = emptyList(),
    val openCarrinhaDeCompraDialog: Boolean = false, // Verifica se a caixa de diálogo para carrinho de venda está anerta
    val openProductSelectorDialog : Boolean = false,
    val showErros: Boolean = false // Diz se pode mostrar os erros.

) {

    // valor total no carrinho de vendas.
    val totalValue: Double
        get() = shoppingCarList.sumOf { it.totalValue }

    // valor total de todas as vendas feitas.
    val totalValueAllVendas: Double
        get() = comprasList.sumOf { it.price }

    // Verifica se o nome do produto está vazio.
    val shoppingCarListError: Boolean get() = showErros && shoppingCarList.isEmpty()
    val finishSaleErrorDialog: Boolean get() = shoppingCarListError

    // Diz se é possível adicionar o produto.
    val isValid: Boolean get() {
        if (shoppingCarList.isEmpty()) {
            return false
        }

        return shoppingCarList.all { item ->
            val price = item.unitPrice.toDoubleOrNull()

            price != null && price > 0
        }
    }

}
