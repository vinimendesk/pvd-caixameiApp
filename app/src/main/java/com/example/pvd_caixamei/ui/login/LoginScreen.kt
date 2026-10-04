package com.example.pvd_caixamei.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pvd_caixamei.ui.theme.PinkDashboard
import com.example.pvd_caixamei.ui.theme.PinkGray

@Composable
fun LoginScreen(
    /*onLoginClick: (String, String) -> Unit = { _, _ -> },
    onCadastroClick: () -> Unit = {}*/
    authViewModel: AuthViewModel,
    modifier: Modifier
) {
    // Estado que armazena o texto digitado no campo de e-mail.
    var email by remember {
        mutableStateOf("")
    }

    // Estado que armazena o texto digitado no campo de senha.
    var senha by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Nome do aplicativo.
        Text(
            text = "PVD CAIXA MEI",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = PinkDashboard
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Pequena descrição abaixo do nome.
        Text(
            text = "Controle seu negócio de forma simples",
            fontSize = 14.sp,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Campo de e-mail.
        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("E-mail")
            },
            placeholder = {
                Text("Digite seu e-mail")
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de senha.
        OutlinedTextField(
            value = senha,
            onValueChange = {
                senha = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text("Senha")
            },
            placeholder = {
                Text("Digite sua senha")
            },
            singleLine = true,

            // Esconde os caracteres da senha.
            visualTransformation = PasswordVisualTransformation(),

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),

            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botão responsável por iniciar o login.
        Button(
            onClick = {
                /*onLoginClick(email, senha)*/
                authViewModel.setLogin()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PinkDashboard
            )
        ) {
            Text(
                text = "Entrar",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Texto informando que o usuário pode realizar o cadastro.
        Text(
            text = "Ainda não possui uma conta?",
            fontSize = 14.sp,
            color = Color.Gray
        )

        TextButton(
            onClick = {/*onCadastroClick*/ }
        ) {
            Text(
                text = "Cadastre-se",
                color = PinkDashboard,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}