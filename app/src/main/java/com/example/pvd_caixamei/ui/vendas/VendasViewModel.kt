package com.example.pvd_caixamei.ui.vendas

import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.withTransaction
import com.example.pvd_caixamei.MainApplication
import com.example.pvd_caixamei.R
import com.example.pvd_caixamei.data.ProdutoEntity
import com.example.pvd_caixamei.data.VendasEntity
import com.example.pvd_caixamei.ui.vendas.componentes.ShoppingCartItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime


class VendasViewModel: ViewModel() {

    // Definindo o UiState como um MutableStateFlow
    private val _vendasUiState = MutableStateFlow(VendasUiState())

    // Coleta do StateFlow do _profileUiState
    val vendasUiState = _vendasUiState.asStateFlow()

    private val vendasDao = MainApplication.pvdDatabase.getVendasDAO()
    private val produtoDao = MainApplication.pvdDatabase.getProdutoDAO()

    init {
        loadAllVendas()
    }

    // ----- Função Abrir Caixa de Diálogo -----
    fun openCarrinhoDeVendasDialog(produtoList: List<ProdutoEntity>) {
        _vendasUiState.update {
            it.copy(
                produtoList = produtoList, // Recebe a lista de produtos para atualizar na tela de carrinho de vendas.
                openCarrinhaDeVendaDialog = true
            )
        }
    }

    fun closeCarrinhoDeVendasDialog() {
        _vendasUiState.update {
            it.copy(openCarrinhaDeVendaDialog = false)
        }
    }

    fun openProductSelectorDialog() {
        _vendasUiState.update {
            it.copy(
                openProductSelectorDialog = true
            )
        }
    }

    fun closeProductSelectorDialog() {
        _vendasUiState.update {
            it.copy(openProductSelectorDialog = false)
        }
    }

    // Adicionar um produto ao carrinho.
    fun addProductToCart(produto: ProdutoEntity) {
        if (produto.estoque <= 0) {
            return
        }

        _vendasUiState.update { state ->

            val alreadyInCart = state.shoppingCarList.any {
                it.produto.produtoId == produto.produtoId
            }
            if (alreadyInCart) {
                state
            } else {
                val newItem = ShoppingCartItem(
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
        _vendasUiState.update { state ->

            state.copy(
                shoppingCarList = state.shoppingCarList.filter {
                    it.produto.produtoId != produtoId
                }
            )
        }
    }

    // Aumentar a quantidade de um produto no carrinho
    fun increaseProductQuantity(produtoId: Int) {

        _vendasUiState.update { state ->

            val updatedCart = state.shoppingCarList.map { item ->

                if (item.produto.produtoId == produtoId) {

                    if (item.quantity < item.produto.estoque) {

                        item.copy(
                            quantity = item.quantity + 1
                        )

                    } else {
                        item
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

    // Diminuir a quantidade de um produto no carrinho
    fun decreaseProductQuantity(produtoId: Int) {

        _vendasUiState.update { state ->

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


    fun showValidationErros(context: Context) {
        _vendasUiState.update {
            it.copy(showErros = true)
        }

        if (vendasUiState.value.finishSaleErrorDialog) {
            Toast.makeText(
                context,
                "Não foi possível finalizar a venda.",
                Toast.LENGTH_SHORT
            ).show()
        }

        viewModelScope.launch {
            _vendasUiState.update {
                delay(1000)
                it.copy(showErros = false)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun finishSale(context: Context) {

        viewModelScope.launch(Dispatchers.IO) {
            try {

                val cart = vendasUiState.value.shoppingCarList

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

                        val estoqueAtualizado = produtoDao.diminuirEstoque(
                            produtoId = item.produto.produtoId,
                            quantidade = item.quantity
                        )

                        if (estoqueAtualizado == 0) {
                            throw IllegalStateException(
                                "Estoque insuficiente para ${item.produto.nome}"
                            )
                        }

                        val venda = VendasEntity(
                            nomeVenda = item.produto.nome,
                            valorVenda = item.produto.price * item.quantity,
                            quantity = item.quantity,
                            dateTime = LocalDateTime.now()
                        )

                        vendasDao.addVendas(venda)

                    }
                }

            withContext(Dispatchers.Main) {

                Toast.makeText(
                    context,
                    "Venda realizada com sucesso.",
                    Toast.LENGTH_SHORT
                ).show()

                Log.d(
                    "RoomDB",
                    "Venda realizada com sucesso."
                )

                _vendasUiState.update {
                    it.copy(
                        shoppingCarList = emptyList(),
                        openCarrinhaDeVendaDialog = false
                    )
                }
            }
        } catch (e: Exception) {

                withContext(Dispatchers.Main) {

                    Toast.makeText(
                        context,
                        "Erro ao realizar venda: $e",
                        Toast.LENGTH_SHORT
                    ).show()

                    Log.e(
                        "RoomDB",
                        "Erro ao realizar venda",
                        e
                    )

                }

            }
        }
    }

    fun addVendas(venda: VendasEntity, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                vendasDao.addVendas(venda)
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "Venda de${venda.nomeVenda} realizada com sucesso.",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d("Roomdb", "Venda de ${venda.nomeVenda} realizada com sucesso.")
                    closeCarrinhoDeVendasDialog()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "Erro ao realizar venda de ${venda.nomeVenda} ${e}",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.e("Roomdb", "Erro realizar venda de ${venda.nomeVenda} ${e}")
                }
            }
        }
    }

    fun loadAllVendas() {
        viewModelScope.launch(Dispatchers.IO) {
            vendasDao.getAllVendas().collect { vendas ->
                _vendasUiState.update {
                    it.copy(vendasList = vendas)
                }
            }
        }
    }

}