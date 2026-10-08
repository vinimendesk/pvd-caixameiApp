package com.example.pvd_caixamei.ui.compras

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.withTransaction
import com.example.pvd_caixamei.MainApplication
import com.example.pvd_caixamei.data.ComprasEntity
import com.example.pvd_caixamei.data.ProdutoEntity
import com.example.pvd_caixamei.data.VendasEntity
import com.example.pvd_caixamei.ui.compras.componentes.ShoppingCartCompraItem
import com.example.pvd_caixamei.ui.vendas.componentes.ShoppingCartItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

class ComprasViewModel: ViewModel() {

    // Definindo o UiState como um MutableStateFlow
    private val _comprasUiState = MutableStateFlow(ComprasUiState())

    // Coleta do StateFlow do _profileUiState
    val comprasUiState = _comprasUiState.asStateFlow()

    private val comprasDao = MainApplication.pvdDatabase.getComprasDAO()
    private val produtoDao = MainApplication.pvdDatabase.getProdutoDAO()

    init {
        loadAllCompras()
    }

    // ----- Função Abrir Caixa de Diálogo -----
    fun openCarrinhoDeVendasDialog(produtoList: List<ProdutoEntity>) {
        _comprasUiState.update {
            it.copy(
                produtoList = produtoList, // Recebe a lista de produtos para atualizar na tela de carrinho de vendas.
                openCarrinhaDeCompraDialog = true
            )
        }
    }

    fun closeCarrinhoDeVendasDialog() {
        _comprasUiState.update {
            it.copy(openCarrinhaDeCompraDialog = false)
        }
    }

    fun openProductSelectorDialog() {
        _comprasUiState.update {
            it.copy(
                openProductSelectorDialog = true
            )
        }
    }

    fun closeProductSelectorDialog() {
        _comprasUiState.update {
            it.copy(openProductSelectorDialog = false)
        }
    }

    // Adicionar um produto ao carrinho.
    fun addProductToCart(produto: ProdutoEntity) {

        _comprasUiState.update { state ->

            val alreadyInCart = state.shoppingCarList.any {
                it.produto.produtoId == produto.produtoId
            }
            if (alreadyInCart) {
                state
            } else {
                val newItem = ShoppingCartCompraItem(
                    produto = produto,
                    quantity = 1
                )

                state.copy(
                    shoppingCarList = state.shoppingCarList + newItem
                )
            }

        }
    }

    // Remover um produto do carrinho.
    fun removeProductFromCart(produtoId: Int) {
        _comprasUiState.update { state ->

            state.copy(
                shoppingCarList = state.shoppingCarList.filter {
                    it.produto.produtoId != produtoId
                }
            )
        }
    }

    // Aumentar a quantidade de um produto no carrinho
    fun increaseProductQuantity(produtoId: Int) {

        _comprasUiState.update { state ->

            val updatedCart = state.shoppingCarList.map { item ->

                if (item.produto.produtoId == produtoId) {


                    item.copy(
                        quantity = item.quantity + 1
                    )

                } else {
                    item
                }

            }

            state.copy(
                shoppingCarList = updatedCart
            )

        }

    }

    // Diminuir a quantidade de um produto no carrinho
    fun decreaseProductQuantity(produtoId: Int) {

        _comprasUiState.update { state ->

            val updatedCart = state.shoppingCarList.mapNotNull { item ->

                if (item.produto.produtoId == produtoId) {

                    if (item.quantity > 1) {

                        item.copy(
                            quantity = item.quantity - 1
                        )

                    } else {
                        null
                    }

                } else {
                    item
                }

            }

            state.copy(
                shoppingCarList = updatedCart
            )

        }

    }

    fun updatePurchaseUnitPrice(
        produtoId: Int,
        price: String
    ) {
        _comprasUiState.update { state ->

            val updatedCart = state.shoppingCarList.map { item ->

                if (item.produto.produtoId == produtoId) {

                    // Atualiza somente o preço do produto alterado.
                    item.copy(
                        unitPrice = price
                    )

                } else {
                    item
                }
            }

            state.copy(
                shoppingCarList = updatedCart
            )
        }
    }


    fun showValidationErros(context: Context) {
        _comprasUiState.update {
            it.copy(showErros = true)
        }

        if (comprasUiState.value.finishSaleErrorDialog) {
            Toast.makeText(
                context,
                "Não foi possível finalizar a compra.",
                Toast.LENGTH_SHORT
            ).show()
        }

        viewModelScope.launch {
            _comprasUiState.update {
                delay(1000)
                it.copy(showErros = false)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun finishBuy(context: Context) {

        viewModelScope.launch(Dispatchers.IO) {
            try {

                val cart = comprasUiState.value.shoppingCarList

                if (cart.isEmpty()) {

                    withContext(Dispatchers.Main) {
                        Toast.makeText(
                            context,
                            "Coloque algum item no carrinho",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    return@launch
                }

                MainApplication.pvdDatabase.withTransaction {

                    cart.forEach { item ->

                        /*
                        * Converte o preço informado pelo usuário
                        * de String para Double.
                        */
                        val unitPrice = item.unitPrice.toDoubleOrNull()

                        /*
                         * Se o preço não for válido, interrompemos
                         * toda a transação.
                         */
                        if (unitPrice == null || unitPrice <= 0) {

                            throw IllegalStateException(
                                "Informe um preço de compra válido para ${item.produto.nome}"
                            )
                        }

                        val totalCompra = unitPrice * item.quantity

                        produtoDao.aumentarEstoque(
                            produtoId = item.produto.produtoId,
                            quantidade = item.quantity
                        )

                        val compra = ComprasEntity(
                            nomeCompra = item.produto.nome,
                            dateTime = LocalDateTime.now(),
                            price = totalCompra,
                            quantity = item.quantity
                        )

                        comprasDao.addCompras(compra)

                    }
                }

                withContext(Dispatchers.Main) {

                    Toast.makeText(
                        context,
                        "compra realizada com sucesso.",
                        Toast.LENGTH_SHORT
                    ).show()

                    Log.d(
                        "RoomDB",
                        "compra realizada com sucesso."
                    )

                    _comprasUiState.update {
                        it.copy(
                            shoppingCarList = emptyList(),
                            openCarrinhaDeCompraDialog = false
                        )
                    }
                }
            } catch (e: Exception) {

                withContext(Dispatchers.Main) {

                    Toast.makeText(
                        context,
                        "Erro ao realizar compra: $e",
                        Toast.LENGTH_SHORT
                    ).show()

                    Log.e(
                        "RoomDB",
                        "Erro ao realizar compra",
                        e
                    )

                }

            }
        }
    }

    fun addCompra(compra: ComprasEntity, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                comprasDao.addCompras(compra)
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "compra de${compra.nomeCompra} realizada com sucesso.",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d("Roomdb", "compra de ${compra.nomeCompra} realizada com sucesso.")
                    closeCarrinhoDeVendasDialog()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "Erro ao realizar compra de ${compra.nomeCompra} ${e}",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e("Roomdb", "Erro realizar compra de ${compra.nomeCompra} ${e}")
                }
            }
        }
    }

    fun loadAllCompras() {
        viewModelScope.launch(Dispatchers.IO) {
            comprasDao.getAllCompras().collect { compras ->
                _comprasUiState.update {
                    it.copy(comprasList = compras)
                }
            }
        }
    }
}

