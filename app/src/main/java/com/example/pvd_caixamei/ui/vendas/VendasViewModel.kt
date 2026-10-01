package com.example.pvd_caixamei.ui.vendas

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pvd_caixamei.MainApplication
import com.example.pvd_caixamei.R
import com.example.pvd_caixamei.data.ProdutoEntity
import com.example.pvd_caixamei.data.VendasEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class VendasViewModel: ViewModel() {

    // Definindo o UiState como um MutableStateFlow
    private val _vendasUiState = MutableStateFlow(VendasUiState())

    // Coleta do StateFlow do _profileUiState
    val vendasUiState = _vendasUiState.asStateFlow()

    val vendasDao = MainApplication.pvdDatabase.getVendasDAO()

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

   /* fun calculateTotalValue() {

        _vendasUiState.update {
            it.copy(totalValue = 0.0)
        }
        var value = 0.0
        _vendasUiState.value.produtoList.forEach { produto ->
            value += produto.price
        }
        _vendasUiState.update {
            it.copy(totalValue = value)
        }
    }*/

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