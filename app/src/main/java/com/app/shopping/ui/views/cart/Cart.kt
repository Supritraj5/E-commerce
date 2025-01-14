package com.app.shopping.ui.views.cart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.app.shopping.data.local.entities.CartEntity
import com.app.shopping.ui.viewmodels.CartViewModel
import com.app.shopping.ui.views.UiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Cart(modifier: Modifier = Modifier) {
    val viewModel: CartViewModel = hiltViewModel()

    val uiState by viewModel.uiState.observeAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cart") }
            )
        },
    ) { paddingValues ->
        Column {
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
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = message, fontSize = 20.sp)
                    }
                }

                is UiState.Success -> {
                    val products = (uiState as UiState.Success<List<CartEntity>>).data
                    if (products.isEmpty()) {
                        Box(modifier = Modifier.fillMaxSize()) {
                            Text(text = "Cart is Empty!")
                        }
                    } else {
                        CartListing(
                            modifier = Modifier.padding(paddingValues),
                            cartProducts = products,
                            onDeleteCartData = {
                                viewModel.deleteCartItem(it)
                            }
                        )
                    }
                }

                null -> TODO()
            }
        }
    }
}