package com.example.pvd_caixamei.ui.produtos.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.pvd_caixamei.R
import com.example.pvd_caixamei.animations.errorContainerColor
import com.example.pvd_caixamei.animations.errorTextColor
import com.example.pvd_caixamei.animations.shakeAnimation
import com.example.pvd_caixamei.ui.theme.PinkDashboard

@Composable
fun NewProductDialog(
    productName: String,
    categoryName: String,
    productPrice: String,
    quantity: String,
    onCategoryChange: (String) -> Unit,
    onProductNameChange: (String) -> Unit,
    onProductPriceChange: (String) -> Unit,
    onQuantityChange: (String) -> Unit,
    onDismissRequest: () -> Unit,
    isValid: Boolean,
    showValidationErros: () -> Unit,
    addNewProduct: () -> Unit,
    productNameError: Boolean,
    categoryNameError: Boolean,
    productPriceError: Boolean,
    productQuantityError: Boolean,
    modifier: Modifier
) {

    // Variáveis TextFields.
    val productNameError = productNameError
    val productNameShake = shakeAnimation(productNameError, null)
    val productNameColor = errorContainerColor(productNameError, null)
    val productNameText = errorTextColor(productNameError, null)

    val categoryError = categoryNameError
    val categoryShake = shakeAnimation(categoryError, null)
    val categoryColor = errorContainerColor(categoryError, null)
    val categoryText = errorTextColor(categoryError, null)

    val priceError = productPriceError
    val priceShake = shakeAnimation(priceError, null)
    val priceColor = errorContainerColor(priceError, null)
    val priceText = errorTextColor(priceError, null)

    val quantityError = productQuantityError
    val quantityShake = shakeAnimation(productQuantityError, null)
    val quantityColor = errorContainerColor(productQuantityError, null)
    val quantityText = errorTextColor(productQuantityError, null)

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(dismissOnClickOutside = false)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(390.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .padding(horizontal = 18.dp)
        ) {

                // Novo Produto + Botão de Fechar
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 4.dp, bottom = 16.dp)
                ) {

                    Text(
                        text = stringResource(R.string.NewProduct_novo_produto),
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                    )

                    IconButton(
                        onClick = { onDismissRequest() }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(R.string.NewProduct_fechar_adicionar_produto),
                            tint = Color.Gray
                        )
                    }
                }

            // Nome do produto
            Text(
                text = stringResource(R.string.NewProduct_nome_do_produto),
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(bottom = 4.dp)
            )

            OutlinedTextField(
                value = productName,
                onValueChange = { text -> onProductNameChange(text) },
                singleLine = true,
                placeholder = {
                    Text(
                        text = stringResource(R.string.NewProduct_ex_gua_mineral_500ml),
                        color = productNameText,
                    )
                              },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                shape = RoundedCornerShape(14.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = productNameColor,
                    unfocusedContainerColor = productNameColor,
                    disabledContainerColor = productNameColor,
                    errorContainerColor = productNameColor
                ),
                modifier = Modifier
                    .graphicsLayer( translationX = productNameShake )
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(bottom = 12.dp)
            )

            // Categoria do Produto
            Text(
                text = stringResource(R.string.NewProduct_categoria_do_produto),
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(bottom = 4.dp)
            )

            OutlinedTextField(
                value = categoryName,
                onValueChange = { text -> onCategoryChange(text) },
                singleLine = true,
                placeholder = {
                    Text(
                        text = stringResource(R.string.NewProduct_ex_bebidas),
                        color = categoryText,
                    )
                },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                shape = RoundedCornerShape(14.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = categoryColor,
                    unfocusedContainerColor = categoryColor,
                    disabledContainerColor = categoryColor,
                    errorContainerColor = categoryColor
                ),
                modifier = Modifier
                    .graphicsLayer( translationX = categoryShake )
                    .fillMaxWidth()
                    .height(60.dp)
                    .padding(bottom = 12.dp)
            )

            // Valor (R$) + Quantidade
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                Column(
                    modifier = Modifier
                ) {

                    // Valor (R$)
                    Text(
                        text = stringResource(R.string.NewProduct_pre_o_r),
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                    )

                    OutlinedTextField(
                        value = productPrice.toString(),
                        onValueChange = { text -> onProductPriceChange(text) },
                        singleLine = true,
                        placeholder = {
                            Text(
                                text = "0.00",
                                color = priceText,
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = priceColor,
                            unfocusedContainerColor = priceColor,
                            disabledContainerColor = priceColor,
                            errorContainerColor = priceColor
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Next),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .graphicsLayer( translationX = priceShake )
                            .width(125.dp)
                            .height(60.dp)
                            .padding(bottom = 12.dp)
                    )

                }
                Column(
                    modifier = Modifier
                ) {

                    // Estoque Inicial
                    Text(
                        text = stringResource(R.string.ProdutosDialog_estoque_inicial),
                        fontSize = 14.sp,
                        modifier = Modifier
                            .padding(bottom = 4.dp)
                    )

                    OutlinedTextField(
                        value = quantity.toString(),
                        onValueChange = { text -> onQuantityChange(text) },
                        singleLine = true,
                        placeholder = {
                            Text(
                                text = "0",
                                color = quantityText,
                            )
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = quantityColor,
                            unfocusedContainerColor = quantityColor,
                            disabledContainerColor = quantityColor,
                            errorContainerColor = quantityColor
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
                        shape = RoundedCornerShape(14.dp),
                        modifier = Modifier
                            .graphicsLayer( translationX = quantityShake )
                            .width(125.dp)
                            .height(60.dp)
                            .padding(bottom = 12.dp)
                    )

                }
            }

            // Button "Adicionar Produto"
            Button(
                onClick = {

                    showValidationErros()

                    if (isValid) {
                        addNewProduct()
                    }
                          },
                colors = ButtonDefaults.buttonColors(containerColor = PinkDashboard),
                modifier = Modifier
                    .fillMaxWidth()
            ) { 
                Text(
                    text = stringResource(R.string.NewProduct_adicionar_produto),
                    color = Color.White
                )
            }


        }

    }
}

@Preview
@Composable
fun NewProductDialogPreview() {

    /*NewProductDialog(
        productName = "",
        categoryName = "",
        productPrice = 0.00,
        quantity = 0,
        onProductPriceChange = {  },
        onCategoryChange = {  },
        onProductNameChange = {  },
        onQuantityChange = {  },
        onDismissRequest = {  },
        isValid = true,
        showValidationErros = {  },
        addNewProduct = {  },
        categoryNameError = false,
        productNameError = false,
        modifier = Modifier
    )*/

}