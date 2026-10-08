package com.example.pvd_caixamei.ui.compras.componentes

import com.example.pvd_caixamei.data.ProdutoEntity

data class ShoppingCartCompraItem(
    val produto: ProdutoEntity,
    val quantity: Int = 1,
    // Preço de compra unitário digitado pelo usuário.
    val unitPrice: String = ""
) {

    // Calcula o valor total do item.
    val totalValue: Double
        get() {
            val price = unitPrice.toDoubleOrNull() ?: 0.0
            return price * quantity
        }
}

