package com.app.shopping.ui.views.home

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.app.shopping.data.local.entities.ProductEntity
import com.app.shopping.navigation.Routes
import com.app.shopping.ui.viewmodels.ProductViewModel
import com.app.shopping.ui.views.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(modifier: Modifier = Modifier, navController: NavHostController) {
    val context = LocalContext.current
    val viewModel: ProductViewModel = hiltViewModel()

    val uiState by viewModel.uiState.observeAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Products") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(route = Routes.Cart) }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
    ) { paddingValues ->
        Column(
            modifier = Modifier
        ) {
            when (uiState) {
                is UiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(modifier = Modifier.padding(paddingValues))
                    }
                }

                is UiState.Error<*> -> {
                    val message = (uiState as UiState.Error<*>).message
                    Text(text = message)
                }

                is UiState.Success -> {
                    val products = (uiState as UiState.Success<List<ProductEntity>>).data
                    ProductListing(
                        modifier = Modifier.padding(paddingValues),
                        products = products,
                        onAddProductToCart = { productToAdd ->
                            viewModel.addProductToCart(productToAdd)
                            Toast.makeText(
                                context,
                                "Product was added to Cart !",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    )
                }

                null -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = "Something went wrong, try again!", fontSize = 20.sp)
                    }
                }
            }

        }
    }

}