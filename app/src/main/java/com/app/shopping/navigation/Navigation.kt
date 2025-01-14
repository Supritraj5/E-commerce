package com.app.shopping.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.app.shopping.ui.views.cart.CartScreen
import com.app.shopping.ui.views.home.Home
import com.app.shopping.ui.views.summary.SummaryScreen

@Composable
fun Navigation(modifier: Modifier = Modifier, navHostController: NavHostController) {

    NavHost(navController = navHostController, startDestination = Routes.Home) {
        composable<Routes.Home> {
            Home(modifier = Modifier, navController = navHostController)
        }

        composable<Routes.Cart> {
            CartScreen(navController = navHostController)
        }

        composable<Routes.Summary> {
            val args = it.toRoute<Routes.Summary>()
            SummaryScreen(itemCount = args.itemCount, amount = args.total)
        }

    }

}