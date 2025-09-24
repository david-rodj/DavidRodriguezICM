package edu.javeriana.davidrodriguezicm.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListScreen(number: Int, navController: NavController) {
    // Calcular la sucesión triangular
    val triangularNumbers = remember {
        mutableStateListOf<Int>().apply {
            for (i in 0..number) {
                add(i * (i + 1) / 2)
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // TopAppBar con botón de regreso
        TopAppBar(
            title = {
                Text(
                    "Sucesión Triangular",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Volver"
                    )
                }
            }
        )

        // Texto informativo
        Text(
            text = "Sucesión triangular hasta el número $number:",
            fontSize = 16.sp,
            modifier = Modifier.padding(16.dp)
        )

        // LazyColumn con los números triangulares
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(triangularNumbers) { index, triangularNumber ->
                ListItem(
                    headlineContent = {
                        Text(
                            text = "Posición $index: $triangularNumber",
                            fontSize = 16.sp
                        )
                    },
                    supportingContent = {
                        if (index == 0) {
                            Text("T₀ = 0")
                        } else {
                            Text("T₍$index₎ = $index × ($index + 1) ÷ 2 = $triangularNumber")
                        }
                    }
                )

                if (index < triangularNumbers.size - 1) {
                    HorizontalDivider()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ListScreenPreview() {
    MaterialTheme {
        ListScreen(number = 5, navController = rememberNavController())
    }
}

@Preview(showBackground = true)
@Composable
private fun ListScreenLargePreview() {
    MaterialTheme {
        ListScreen(number = 10, navController = rememberNavController())
    }
}