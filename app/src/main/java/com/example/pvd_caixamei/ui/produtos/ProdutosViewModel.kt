package com.example.pvd_caixamei.ui.produtos

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pvd_caixamei.MainApplication
import com.example.pvd_caixamei.R
import com.example.pvd_caixamei.data.ProdutoEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProdutosViewModel: ViewModel() {

    // Definindo o UiState como um MutableStateFlow
    private val _produtosUiState = MutableStateFlow(ProdutosUiState())

    // Coleta do StateFlow do _profileUiState
    val produtosUiState = _produtosUiState.asStateFlow()

    val produtoDao = MainApplication.pvdDatabase.getProdutoDAO()

    init {
        /*deleteAllProduct()*/
        loadAllProducts()
    }

    fun loadAllProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            produtoDao.getAllProduto().collect { produtos ->
                _produtosUiState.update {
                    it.copy(produtoList = produtos)
                }
            }
        }
        /*countProdutos()*/
    }

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

    /* FUNÇÕES DAO */
    fun addProduto(produto: ProdutoEntity, context: Context) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                produtoDao.addProduto(produto)
                // Alterna para a Thread Principal apenas para exibir a UI e atualizar o estado
                withContext(Dispatchers.Main) {
                    limparProdutoState()
                    Toast.makeText(
                        context,
                        "${produto.nome} foi adicionado com sucesso.",
                        Toast.LENGTH_SHORT
                    ).show()
                    Log.d("RooomDB","${produto.nome} foi adicionado com sucesso.")
                    /*countProdutos()*/
                    closeAddProduct()
                }
            } catch (error: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(
                        context,
                        "erro ao adicionar ${produto.nome}.",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                Log.e("RoomDB","erro ao adicionar ${produto.nome}. ${error}")
            }
        }
    }

    // Exclui aluno ao banco de dados.
    fun deleteProduto(produto: ProdutoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            produtoDao.deleteProduto(produto)
        }
    }

    // Atualiza um novo aluno ao banco de dados.
    fun updateProduto(produto: ProdutoEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            produtoDao.updateProduto(produto)
        }
    }

    fun limparProdutoState() {
        _produtosUiState.update {
            it.copy(
                productName = "",
                productPrice = 0.0,
                productQuantity = 0,
                categoryName = "",
            )
        }
    }

    /*fun countProdutos() {
        _produtosUiState.update {
            it.copy(
                productNumber = _produtosUiState.value.produtoList.size
            )
        }
    }*/

    fun deleteAllProduct() {
        produtoDao.deleteAllProducts()
    }

}