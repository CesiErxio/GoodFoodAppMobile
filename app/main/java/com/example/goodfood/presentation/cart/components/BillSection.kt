package com.example.goodfood.presentation.cart.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.example.goodfood.presentation.common.round
import kotlinx.coroutines.launch
import kotlin.math.ceil

@Composable
fun BillSection(
    itemTotal: Double,
    restaurantId: Int,
    menuItemIds: List<Int>,
    noOfItems: Int,
    navController: NavHostController,
    viewModel: BillSectionViewModel = hiltViewModel()
) {
    val userId by viewModel.userId.collectAsState(initial = "")
    val taxAndFees = ceil((0.18) * itemTotal).toInt()
    val totalPrice = ceil(itemTotal + ((0.18) * itemTotal)).toInt()

//    val totalPrice = (itemTotal + ((0.18) * itemTotal).round(2)).round(2)


    Column(modifier = Modifier.padding(16.dp)) {
        Card(
            shape = RoundedCornerShape(24.dp),
            elevation = 16.dp
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Détails de la facture", fontWeight = FontWeight.Bold)
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Column {
                        Text(text = "Total des articles:")
                        Text(text = "Taxes et frais:")
                        Text(text = "Total:")
                    }
                    Column {
                        Text(text = "€$itemTotal")
                        Text(text = "€$taxAndFees")
                        Text(text = "€$totalPrice")
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                if (totalPrice.toInt() != 0){
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(onClick = {
                            viewModel.viewModelScope.launch {
                                viewModel.addOrder(
                                    restaurantId = restaurantId,
                                    userId = userId,
                                    mealsIds = menuItemIds.map { it },
                                    noOfItems = noOfItems,
                                    totalPrice = totalPrice
                                )
                            }
                            viewModel.clearCartItem()
                            navController.navigateUp()
                        }) {
                            Text(text = "Procéder au paiement")
                        }
                    }
                }
            }
        }
    }
}

