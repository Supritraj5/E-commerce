package com.app.shopping.navigation

import kotlinx.serialization.Serializable

sealed class Routes {
    @Serializable
    object Home

    @Serializable
    object Cart

    @Serializable
    data class Summary(
        val itemCount: Int,
        val total: Double,
    )
}