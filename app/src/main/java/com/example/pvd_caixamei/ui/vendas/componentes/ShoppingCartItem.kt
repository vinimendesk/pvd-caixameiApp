package com.example.pvd_caixamei.ui.vendas.componentes

import com.example.pvd_caixamei.data.ProdutoEntity

data class ShoppingCartItem(
    val produto: ProdutoEntity,
    val quantity: Int = 1
) {
    val totalValue: Double
        get() = produto.price * quantity
}
