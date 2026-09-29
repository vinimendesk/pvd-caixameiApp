package com.example.pvd_caixamei.nav

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Dashboard
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItemContent(
    val screenType: ScreenType,
    val icon: ImageVector,
    val text: String
)

// Objeto navigationItemContentList
object NavigationItemContentList {
    @Composable
    fun getNavigationContentList(): List<NavigationItemContent> {
        return listOf(
            NavigationItemContent(
                screenType = ScreenType.Dashboard,
                icon = Icons.Outlined.Dashboard,
                text = ScreenType.Dashboard.name
            ),
            NavigationItemContent(
                screenType = ScreenType.Produtos,
                icon = Icons.Outlined.Inventory2,
                text = ScreenType.Produtos.name
            ),
            NavigationItemContent(
                screenType = ScreenType.Vendas,
                icon = Icons.Outlined.ShoppingCart,
                text = ScreenType.Vendas.name
            ),
            NavigationItemContent(
                screenType = ScreenType.Compras,
                icon = Icons.Outlined.ShoppingBag,
                text = ScreenType.Compras.name
            )
        )
    }
}