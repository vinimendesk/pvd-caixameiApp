
package com.example.pvd_caixamei.ui.compras.componentes

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pvd_caixamei.ui.theme.PinkBottomNavigationBar
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ComprasRecentesCard(
    dateTime: LocalDateTime,
    value: Double,
    itemName: String,
    unit: Int,
    modifier: Modifier
) {

    // Formata a data da compra para o padrão brasileiro.
    val dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

    // Formata o horário da compra.
    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    // Converte a data/hora para texto.
    val date = dateTime.format(dateFormatter)
    val hour = dateTime.format(timeFormatter)

    OutlinedCard(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .height(80.dp)
    ) {

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 12.dp)
        ) {

            // Informações do produto comprado.
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
            ) {

                // Data e horário da compra.
                Text(
                    text = "$date $hour",
                    color = Color.Gray,
                    fontSize = 10.sp
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                // Produto e quantidade comprada.
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(24.dp))
                        .background(PinkBottomNavigationBar)
                        .padding(
                            horizontal = 8.dp,
                            vertical = 6.dp
                        )
                ) {

                    Text(
                        text = "$itemName x $unit",
                        fontSize = 10.sp
                    )
                }
            }

            // Informações financeiras da compra.
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxHeight()
            ) {

                // Valor total daquela compra.
                //
                // Exemplo:
                // 100 unidades × R$ 8,90 = R$ 890,00
                Text(
                    text = "R$ %.2f".format(value),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Red
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                // Texto indicando que o valor exibido
                // representa o total da compra.
                Text(
                    text = "Total da compra",
                    color = Color.Gray,
                    fontWeight = FontWeight.Bold,
                    fontSize = 11.sp
                )
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview
@Composable
fun ComprasRecentesCardPreview() {

    ComprasRecentesCard(
        dateTime = LocalDateTime.now(),
        value = 890.00,
        itemName = "Cerveja",
        unit = 100,
        modifier = Modifier
    )
}
