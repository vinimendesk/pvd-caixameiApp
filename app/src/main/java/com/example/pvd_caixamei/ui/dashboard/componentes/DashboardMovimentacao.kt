package com.example.pvd_caixamei.ui.dashboard

import java.time.LocalDateTime

data class DashboardMovimentacao(
    val nome: String,
    val quantidade: Int,
    val valor: Double,
    val dateTime: LocalDateTime,

    // 0 = venda
    // 1 = compra
    val type: Int
)