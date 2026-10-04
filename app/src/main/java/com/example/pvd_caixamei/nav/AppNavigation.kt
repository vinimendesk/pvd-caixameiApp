package com.example.pvd_caixamei.nav

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.pvd_caixamei.data.auth.AuthState
import com.example.pvd_caixamei.ui.dashboard.DashboardUI
import com.example.pvd_caixamei.ui.login.AuthViewModel
import com.example.pvd_caixamei.ui.login.LoginScreen
import com.example.pvd_caixamei.ui.produtos.ProdutosUI
import com.example.pvd_caixamei.ui.produtos.ProdutosViewModel
import com.example.pvd_caixamei.ui.vendas.VendasUI
import com.example.pvd_caixamei.ui.vendas.VendasViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(
    modifier: Modifier
) {

    val authViewModel = remember { AuthViewModel() }
    val authState by authViewModel.state.collectAsState()

    when(authState) {

        AuthState.Loading -> {
            CircularProgressIndicator(modifier = modifier.fillMaxSize())
        }

        AuthState.LoggedOut -> {
            AuthNavigation(
                authViewModel = authViewModel,
                modifier = modifier
            )
        }

        AuthState.LoggedIn -> {
            MainNavigation(
                modifier = Modifier
            )
        }

        is AuthState.Error -> {
            AuthNavigation(
                authViewModel = authViewModel,
                modifier = modifier
            )
        }

    }

    /*MainNavigation(modifier = modifier)*/

}

@Composable
fun AuthNavigation(
    authViewModel: AuthViewModel,
    modifier: Modifier
) {

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {  paddingValues ->

        LoginScreen(
            authViewModel = authViewModel,
            modifier = modifier.padding(paddingValues)
        )

    }

}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainNavigation(
    modifier: Modifier
) {


    val navController = rememberNavController()
    // Retorna o estado atual da pilha de navegação.
    val navBackStackEntry = navController.currentBackStackEntryAsState()
    // Acessamos a rota do composable atual.
    val currentDestination = navBackStackEntry.value?.destination?.route
    // Procura no ScreenType, qual a rota atual, caso não encontre, retorne Dashboard.
    val currentScreen = ScreenType.entries.find { it.name == currentDestination } ?: ScreenType.Dashboard

    // Instâncias do ViewModel e UiState.
    val produtosViewModel: ProdutosViewModel = viewModel()
    val produtosUiState = produtosViewModel.produtosUiState.collectAsState()

    val vendasViewModel: VendasViewModel = viewModel()
    val vendasUiState = vendasViewModel.vendasUiState.collectAsState()

    val context = LocalContext.current

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                onTabPressed = { navController.navigate(it) },
                navigationItemContentList = NavigationItemContentList.getNavigationContentList(),
                currentScreen = currentScreen,
                modifier = Modifier.height(105.dp)
            )
        },
        modifier =  Modifier.fillMaxSize()
    ) { paddingValues ->

        NavHost(
            navController = navController,
            startDestination = ScreenType.Dashboard.name,
            modifier = Modifier.padding(paddingValues)
        ) {

            // Tela Dashboard
            composable(ScreenType.Dashboard.name) {
                DashboardUI(
                    vendasUiState = vendasUiState,
                    modifier = Modifier.padding(paddingValues)
                )
            }

            composable(ScreenType.Produtos.name) {
                ProdutosUI(
                    produtosViewModel = produtosViewModel,
                    produtosUiState = produtosUiState,
                    context = context,
                    modifier = Modifier.padding(paddingValues)
                )
            }

            composable(ScreenType.Vendas.name) {
                VendasUI(
                    vendasViewModel = vendasViewModel,
                    vendasUiState = vendasUiState,
                    produtosUiState = produtosUiState,
                    context = context,
                    modifier = Modifier.padding(paddingValues)
                )
            }

            composable(ScreenType.Compras.name) {
                ProdutosUI(
                    produtosViewModel = produtosViewModel,
                    produtosUiState = produtosUiState,
                    context = context,
                    modifier = Modifier.padding(paddingValues)
                )
            }

        }

    }
}

enum class ScreenType() {
    Dashboard,
    Produtos,
    Vendas,
    Compras
}