package com.example.pvd_caixamei.ui.dashboard

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pvd_caixamei.R
import com.example.pvd_caixamei.ui.compras.ComprasUiState
import com.example.pvd_caixamei.ui.dashboard.componentes.DashboardCard
import com.example.pvd_caixamei.ui.dashboard.componentes.DashboardCardBalanco
import com.example.pvd_caixamei.ui.dashboard.componentes.DashboardCardVendasCompras
import com.example.pvd_caixamei.ui.theme.PinkDashboard
import com.example.pvd_caixamei.ui.theme.PinkGray
import com.example.pvd_caixamei.ui.vendas.VendasUiState
import com.example.pvd_caixamei.ui.vendas.componentes.VendasCard
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun DashboardUI(
    vendasUiState: State<VendasUiState>,
    comprasUiState: State<ComprasUiState>,
    modifier: Modifier
) {

    val vendasList = vendasUiState.value.vendasList
    val comprasList = comprasUiState.value.comprasList

    val vendasListOrd = vendasList.sortedByDescending { it.dateTime }
    val comprasListOrd = comprasList.sortedByDescending { it.dateTime }

    // Soma todo o dinheiro recebido através das vendas.
    val totalVendas = vendasList.sumOf { it.valorVenda }

    // Soma todo o dinheiro gasto através das compras.
    val totalCompras = comprasList.sumOf { it.price }

    // Resultado financeiro.
    // Vendas entram, compras saem.
    val balanco = totalVendas - totalCompras

    val movimentacoes = buildList {

        // Adiciona todas as vendas.
        vendasList.forEach { venda ->

            add(
                DashboardMovimentacao(
                    nome = venda.nomeVenda,
                    quantidade = venda.quantity,
                    valor = venda.valorVenda,
                    dateTime = venda.dateTime,
                    type = 0
                )
            )
        }

        // Adiciona todas as compras.
        comprasList.forEach { compra ->

            add(
                DashboardMovimentacao(
                    nome = compra.nomeCompra,
                    quantidade = compra.quantity,
                    valor = compra.price,
                    dateTime = compra.dateTime,
                    type = 1
                )
            )
        }
    }.sortedByDescending { it.dateTime }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {

        // Dashboard + Acompanhe as suas finanças
        Text(
            text = stringResource(R.string.r_dashboard_dashboard),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 8.dp)
        )

        Text(
            text = stringResource(R.string.acompanhe_as_suas_finan_as),
            fontSize = 11.sp,
            color = Color.Gray,
            modifier = Modifier
                .padding(bottom = 24.dp, start = 16.dp)
        )

        // Março 2026
        Card(
            colors = CardDefaults.cardColors(containerColor = PinkGray),
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
                .height(50.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(
                    text = "Acompanhamento Financeiro",
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        // Balanço do Mês
        DashboardCardBalanco(
            value = balanco,
            modifier = Modifier
        )

        // Vendas e Balanço
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            DashboardCardVendasCompras(
                type = 0,
                value = totalVendas,
                modifier = Modifier
            )
            DashboardCardVendasCompras(
                type = 1,
                value = totalCompras,
                modifier = Modifier
            )
        }

        // Atividade Recente
        Text(
            text = stringResource(R.string.dashboard_atividade_recente),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            modifier = Modifier
                .padding(start = 16.dp, top = 16.dp, end = 16.dp, bottom = 26.dp)
        )

        // Lista de produtos no estoque
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 500.dp,),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (movimentacoes.isEmpty()) {

                item {

                    Text(
                        text = stringResource(
                            R.string.dasboard_nenhuma_atividade_neste_m_s
                        ),
                        fontSize = 14.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                bottom = 26.dp,
                                start = 16.dp
                            )
                    )
                }

            } else {

                items(movimentacoes) { movimentacao ->

                    DashboardCard(
                        sellName = movimentacao.nome,
                        type = movimentacao.type,
                        dateTime = movimentacao.dateTime,
                        value = movimentacao.valor,
                        modifier = Modifier
                            .padding(
                                start = 16.dp,
                                end = 16.dp
                            )
                    )
                }
            }
        }

        /*DashboardCard(
            sellName = "Refrigerante 2L",
            type = 0,
            dateTime = LocalDateTime.of(2026, 3, 12, 14, 30),
            value = 2.50,
            modifier = Modifier
                .padding(bottom = 8.dp, start = 16.dp, end = 16.dp)
        )

        DashboardCard(
            sellName = "Caixa de Skol",
            type = 1,
            dateTime = LocalDateTime.of(2026, 3, 12, 18, 22),
            value = 8000.00,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp)
        )*/

    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun DashboardUIPreview() {

    /*DashboardUI(modifier = Modifier)*/

}