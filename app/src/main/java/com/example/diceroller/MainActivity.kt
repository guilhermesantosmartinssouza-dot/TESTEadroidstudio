
package com.example.diceroller

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.diceroller.ui.theme.DiceRollerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            DiceRollerTheme {

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    DiceWithButtonAndText(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .background(Color(0xFFEAF4F8))
                    )
                }
            }
        }
    }
}


/*
 * Função responsável por sortear um número
 * entre 1 e 6.
 */
fun rolarDado(): Int {
    return (1..6).random()
}


/*
 * Converte o número sorteado para
 * o símbolo correspondente de um dado.
 */
fun obterFaceDoDado(valor: Int): String = when (valor) {

    1 -> "⚀"
    2 -> "⚁"
    3 -> "⚂"
    4 -> "⚃"
    5 -> "⚄"
    6 -> "⚅"

    else -> "?"
}


/*
 * Tela principal do Dice Roller.
 */
@Composable
fun DiceWithButtonAndText(
    modifier: Modifier = Modifier
) {

    // Guarda o resultado atual do dado.
    var resultado by remember {
        mutableStateOf(1)
    }

    // Guarda o resultado anterior.
    // Int? significa que pode ser nulo.
    var ultimoResultado by remember {
        mutableStateOf<Int?>(null)
    }


    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Mostra a face atual do dado.
        Text(
            text = obterFaceDoDado(resultado),
            fontSize = 80.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )


        // Mostra o resultado anterior.
        Text(
            text = "Resultado anterior: ${
                ultimoResultado?.toString() ?: "nenhum ainda"
            }",
            fontSize = 14.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )


        // Botão responsável por sortear o dado.
        Button(
            onClick = {

                // Guarda o resultado atual
                // antes de realizar um novo sorteio.
                ultimoResultado = resultado

                // Sorteia um novo número.
                resultado = rolarDado()
            }
        ) {

            Text(
                text = "Rolar"
            )
        }
    }
}
