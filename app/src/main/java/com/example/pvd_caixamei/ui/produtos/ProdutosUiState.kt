package com.example.pvd_caixamei.ui.produtos

import com.example.pvd_caixamei.data.ProdutoEntity

data class ProdutosUiState(
    val produtoList: List<ProdutoEntity> = listOf(), // Lista com os dados dos produtos
    val openAddProductDialog: Boolean = false, // Verifica se a caixa de diálogo para adicionar produto está anerta
    val productId: Int? = null, // Id do produto para busca.
    val productName: String = "", // Nome do produto na caixa de diálogo.
    val categoryName: String = "", // Categoria do produto na caixa de diálogo.
    val productPrice: String = "", // Preço do produto a ser adicionado.
    val productQuantity: String = "", // Quantidade inicial no estoque na caixa de diálogo.
    // val productNumber: Int = 0,  Número de produtos cadastrados
    val showErros: Boolean = false // Diz se pode mostrar os erros.
) {

    val productNumber: Int
        get() = produtoList.size

    // Verifica se o nome do produto está vazio.
    val productNameError: Boolean get() = showErros && productName.isBlank()
    val categoryNameError: Boolean get() = showErros && categoryName.isBlank()
    val productPriceError: Boolean get() = showErros && productPrice.isBlank()
    val productQuantityError: Boolean get() = showErros && productQuantity.isBlank()
    val addProductErrorDialog: Boolean get() = productNameError || categoryNameError

    // Diz se é possível adicionar o produto.
    val isValid: Boolean get() = productName.isNotBlank() && categoryName.isNotBlank()

}