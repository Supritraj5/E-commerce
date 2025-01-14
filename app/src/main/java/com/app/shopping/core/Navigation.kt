package com.app.shopping.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.app.shopping.ui.views.cart.Cart
import com.app.shopping.ui.views.home.Home
import kotlinx.serialization.Serializable

@Composable
fun Navigation(modifier: Modifier = Modifier, navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = Home) {
        composable<Home> {
            Home(modifier = Modifier, navController = navHostController)
        }

        composable<RouteCart> {
            Cart()
        }
    }

}

@Serializable
object Home

@Serializable
object RouteCart