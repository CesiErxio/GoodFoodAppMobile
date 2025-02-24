package com.example.goodfood.presentation.cart

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.goodfood.R
import com.example.goodfood.presentation.cart.components.BillSection
import com.example.goodfood.presentation.cart.components.CouponBar
import com.example.goodfood.presentation.cart.components.ItemSection

@Composable
fun Cart(
    navController: NavHostController,
    viewModel: CartViewModel = hiltViewModel(),
) {
    val list by viewModel.cartState
    val totalAmount = calculateTotalAmount(list)

    val cartItems = list.list.filter { it.noOfItems > 0 }
    val menuItemIds = cartItems.map { it.menuItem.id }
    val noOfItems = cartItems.sumBy { it.noOfItems }

    val context = LocalContext.current as Activity

    context.window.statusBarColor = Color.Gray.toArgb()
    context.window.navigationBarColor = Color.White.toArgb()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFE8E7E7)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(imageVector = Icons.Outlined.Close, contentDescription = stringResource(id = R.string.back))
                }
            }
        }

        item {
            ItemSection(
                list = list.list.filter {
                    it.noOfItems > 0
                },
                onDecreaseClick = { },
                onIncreaseClick = { },
            )
        }

        item {
            CouponBar()
        }
        item {
            BillSection(
                itemTotal = totalAmount,
                navController = navController,
                restaurantId = 1,
                menuItemIds = menuItemIds,
                noOfItems = noOfItems,
            )
        }
    }
}

private fun calculateTotalAmount(cartState: CartState): Double {
    return cartState.list.sumByDouble { it.noOfItems * it.menuItem.price }
}
