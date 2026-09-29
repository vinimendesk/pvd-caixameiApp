package com.example.pvd_caixamei.ui.vendas

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pvd_caixamei.R
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class VendasViewModel: ViewModel() {

    // Definindo o UiState como um MutableStateFlow
    private val _vendasUiState = MutableStateFlow(VendasUiState())

    // Coleta do StateFlow do _profileUiState
    val vendasUiState = _vendasUiState.asStateFlow()

    // ----- Função Abrir Caixa de Diálogo -----
    fun openCarrinhoDeVendasDialog() {
        _vendasUiState.update {
            it.copy(openCarrinhaDeVendaDialog = true)
        }
    }

    fun closeCarrinhoDeVendasDialog() {
        _vendasUiState.update {
            it.copy(openCarrinhaDeVendaDialog = false)
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

}