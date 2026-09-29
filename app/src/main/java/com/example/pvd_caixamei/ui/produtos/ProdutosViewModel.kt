package com.example.pvd_caixamei.ui.produtos

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

class ProdutosViewModel: ViewModel() {

    // Definindo o UiState como um MutableStateFlow
    private val _produtosUiState = MutableStateFlow(ProdutosUiState())

    // Coleta do StateFlow do _profileUiState
    val produtosUiState = _produtosUiState.asStateFlow()

    // ----- Função Abrir Caixa de Diálogo -----
    fun openAddProduct() {
        _produtosUiState.update {
            it.copy(openAddProductDialog = true)
        }
    }

    fun closeAddProduct() {
        _produtosUiState.update {
            it.copy(openAddProductDialog = false)
        }
    }

    fun onProductNameChange(productName: String) {
        _produtosUiState.update {
            it.copy(productName = productName)
        }
    }

    fun onProductCategoryChange(productCategory: String) {
        _produtosUiState.update {
            it.copy(categoryName = productCategory)
        }
    }

    fun onProductPrice(productPrice: String) {
        _produtosUiState.update {
            it.copy(productPrice = productPrice.toDouble())
        }
    }

    fun onProducQuantityChange(productQuantity: String) {
        _produtosUiState.update {
            it.copy(productQuantity = if (productQuantity.isNotBlank()) productQuantity.toInt() else 0)
        }
    }

    fun showValidationErros(context: Context) {
        _produtosUiState.update {
            it.copy(showErros = true)
        }

        if (produtosUiState.value.addProductErrorDialog) {
            Toast.makeText(
                context,
                context.getString(R.string.ProductDialogn_o_foi_poss_vel_adicionar_o_produto),
                Toast.LENGTH_SHORT
            ).show()
        }

        viewModelScope.launch {
           _produtosUiState.update {
               delay(1000)
               it.copy(showErros = false)
           }
        }
    }

}