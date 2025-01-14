package com.app.shopping.ui.views

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.app.shopping.data.local.entities.ProductEntity
import com.app.shopping.ui.viewmodels.ProductViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(modifier: Modifier = Modifier) {

    val viewModel: ProductViewModel = viewModel()

    val uiState by viewModel.uiState.observeAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Home") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        },
        floatingActionButtonPosition = FabPosition.End,
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
                    Text(text = message)
                }

                is UiState.Success -> {
                    val products = (uiState as UiState.Success<List<ProductEntity>>).data
                    ProductListing(
                        modifier = Modifier.padding(paddingValues),
                        products = products,
                        onAddProductToCart = { productToAdd ->

                        }
                    )
                }

                null -> TODO()
            }

        }
    }

}