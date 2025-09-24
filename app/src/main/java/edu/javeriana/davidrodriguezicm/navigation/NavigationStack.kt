package edu.javeriana.davidrodriguezicm.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

class NavigationStack {

    @Composable
    fun NavigationStack() {
        // Crea un controlador de navegación que gestiona la pila de pantallas.
        val navController = rememberNavController()

        // Define el host de navegación, que contiene las rutas y pantallas disponibles.
        NavHost(
            navController = navController, // Asocia el controlador de navegación.
            startDestination = "main_screen" // Define la pantalla inicial.
        ) {
            // Define la pantalla principal ("main_screen").
            composable(route = "main_screen") {
                // Muestra la pantalla principal, pasando el controlador de navegación.
                MainScreen(navController = navController)
            }

            // Define la pantalla de lista ("list_screen") con un argumento opcional.
            composable(
                route = "list_screen" + "?number={number}", // Ruta con un parámetro opcional "number".
                arguments = listOf(
                    navArgument("number") {
                        type = NavType.StringType // El tipo del argumento es String.
                        nullable = true // Permite que el argumento sea nulo.
                    }
                )
            ) {
                // Obtiene el valor del argumento "number" pasado a la pantalla.
                val number = it.arguments?.getString("number")

                // Muestra la pantalla de lista, pasando el número como argumento.
                ListScreen(number = number)
            }
        }
    }
}