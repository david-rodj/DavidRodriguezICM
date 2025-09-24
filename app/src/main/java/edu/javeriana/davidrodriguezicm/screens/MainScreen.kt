package edu.javeriana.davidrodriguezicm.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import edu.javeriana.davidrodriguezicm.R

@Composable
fun MainScreen(navController: NavController) {
    var numberText by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(35.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Título
        Text(
            text = "Sucesión Triangular",
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        // Spacer de 10dp
        Spacer(modifier = Modifier.height(10.dp))

        // Imagen triangular
        Image(
            painter = painterResource(id = R.drawable.triangular),
            contentDescription = "Imagen triangular",
            modifier = Modifier
                .size(220.dp)
                .clip(CircleShape)
        )

        // Spacer de 10dp
        Spacer(modifier = Modifier.height(10.dp))

        // OutlinedTextField para ingresar número
        OutlinedTextField(
            value = numberText,
            onValueChange = { numberText = it },
            label = { Text("Ingrese un número") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        // Spacer de 10dp
        Spacer(modifier = Modifier.height(10.dp))

        // Botón Calcular
        Button(
            onClick = {
                val number = numberText.toIntOrNull()

                if (number == null || number < 0 || number > 50) {
                    Toast.makeText(
                        context,
                        "El número está fuera de rango (0-50)",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    navController.navigate("list_screen/$number")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular")
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    MaterialTheme {
        MainScreen(navController = rememberNavController())
    }
}
