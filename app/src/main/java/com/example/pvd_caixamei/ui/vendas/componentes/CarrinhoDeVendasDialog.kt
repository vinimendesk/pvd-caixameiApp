package com.example.pvd_caixamei.ui.vendas.componentes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Inventory2
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
fun CarrinhoDeVendaDialog(
    onDismissRequest: () -> Unit,
    isValid: Boolean,
    showValidationErros: () -> Unit,
    finishSale: () -> Unit,
    modifier: Modifier
) {

    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(dismissOnClickOutside = false)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(600.dp)
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
                    text = "Carrinho de Venda",
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

            CarrinhoDeVendasCard(
                image = Icons.Outlined.Inventory2,
                productName = "Refrigerante 2L",
                value = 12.00,
                quantity = 50,
                category = "Bebidas",
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(370.dp)) // Na versão final, coloque 430.dp

            // Valor Total + Botão Finalizar Venda
            Row(
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Total",
                    fontSize = 16.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .align(Alignment.Bottom)
                )
                Text(
                    text = "R$ 16.50",
                    fontSize = 22.sp,
                    color = PinkDashboard,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                )
            }

            Spacer(modifier = Modifier.height(12.dp))
11
            // Button "Adicionar Produto"
            Button(
                onClick = {

                    showValidationErros()

                    if (isValid) {
                        finishSale()
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = PinkDashboard),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Finalizar Venda",
                    color = Color.White
                )
            }


        }

    }
}

@Preview
@Composable
fun NewProductDialogPreview() {

    CarrinhoDeVendaDialog(
        onDismissRequest = {  },
        isValid = true,
        showValidationErros = {  },
        finishSale = {  },
        modifier = Modifier
    )

}