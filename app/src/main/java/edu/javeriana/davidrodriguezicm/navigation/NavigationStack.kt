package edu.javeriana.davidrodriguezicm.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import edu.javeriana.davidrodriguezicm.screens.ListScreen
import edu.javeriana.davidrodriguezicm.screens.MainScreen

@Composable
fun NavigationStack(modifier: Modifier = Modifier) {
    // Crea un controlador de navegación que gestiona la pila de pantallas.
    val navController = rememberNavController()

    // Define el host de navegación, que contiene las rutas y pantallas disponibles.
    NavHost(
        navController = navController, // Asocia el controlador de navegación.
        startDestination = "main_screen", // Define la pantalla inicial.
        modifier = modifier
    ) {
        // Define la pantalla principal ("main_screen").
        composable(route = "main_screen") {
            // Muestra la pantalla principal, pasando el controlador de navegación.
            MainScreen(navController = navController)
        }

        // Define la pantalla de lista ("list_screen") con un argumento.
        composable(
            route = "list_screen/{number}", // Ruta con un parámetro "number".
            arguments = listOf(
                navArgument("number") {
                    type = NavType.IntType // El tipo del argumento es Int.
                }
            )
        ) {
            // Obtiene el valor del argumento "number" pasado a la pantalla.
            val number = it.arguments?.getInt("number") ?: 0

            // Muestra la pantalla de lista, pasando el número como argumento.
            ListScreen(number = number, navController = navController)
        }
    }
}