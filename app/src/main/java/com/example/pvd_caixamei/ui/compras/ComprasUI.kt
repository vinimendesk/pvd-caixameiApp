package com.example.pvd_caixamei.ui.compras

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pvd_caixamei.ui.theme.PinkDashboard
import com.example.pvd_caixamei.ui.vendas.VendasUiState
import com.example.pvd_caixamei.ui.vendas.VendasViewModel
import com.example.pvd_caixamei.ui.vendas.componentes.CarrinhoDeVendaDialog
import com.example.pvd_caixamei.ui.vendas.componentes.VendasCard

@Composable
fun ComprasUI(
    vendasViewModel: VendasViewModel,
    vendasUiState: State<VendasUiState>,
    context: Context,
    modifier: Modifier
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(start = 16.dp, top = 16.dp, end = 16.dp)
    ) {

        // Produtos + Botão de Adicionar
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 28.dp)
        ) {

            // Produtos + 5 produtos em estoque
            Column {

                Text(
                    text = "Nova Compra",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black,
                    modifier = Modifier
                        .padding(bottom = 8.dp)
                )

                Text(
                    text = "Registre suas compras",
                    fontSize = 11.sp,
                    color = Color.Gray,
                    modifier = Modifier
                )

            }

            // Botão Adicionar Itens
            Box(
                modifier = Modifier
                    .height(40.dp)
                    .width(85.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(PinkDashboard)
                    .clickable { vendasViewModel.openCarrinhoDeVendasDialog() },
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 12.dp)
                ) {
                    Icon(
                        imageVector = Icons.Outlined.ShoppingBag,
                        contentDescription = "Realizar comprar",
                        tint = Color.White,
                        modifier = Modifier
                            .size(15.dp)
                    )

                    Text(
                        text = "Itens",
                        color = Color.White,
                        fontSize = 14.sp,
                    )
                }
            }



        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(14.dp))
                .background(PinkDashboard)
                .height(50.dp)
                .clickable {  }
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    tint = Color.White,
                    contentDescription = "Adicionar item",
                    modifier = Modifier
                        .size(20.dp)
                )
                
                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Adicionar item",
                    color = Color.White,
                    fontSize = 16.sp
                )
            }

        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Compras Recentes",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(20.dp))

        // Lista de produtos no estoque
        VendasCard(
            image = null,
            productName = "Refrigerante 2L",
            value = 12.00,
            quantity = 50,
            category = "Bebidas",
            modifier = Modifier
        )

    }

    // Caixas de Diálogos
    if (vendasUiState.value.openCarrinhaDeVendaDialog) {
        CarrinhoDeVendaDialog(
            onDismissRequest = { vendasViewModel.closeCarrinhoDeVendasDialog() },
            isValid = true,
            showValidationErros = { vendasViewModel.showValidationErros(context) },
            finishSale = {  },
            modifier = Modifier
        )
    }

}

@Preview
@Composable
fun VendasUIPreview() {

    val vendasViewModel: VendasViewModel = viewModel()
    val vendasUiState = vendasViewModel.vendasUiState.collectAsState()
    val context = LocalContext.current

    ComprasUI(
        vendasViewModel,
        vendasUiState,
        context,
        Modifier
    )

}