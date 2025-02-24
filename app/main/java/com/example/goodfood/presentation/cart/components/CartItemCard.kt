package com.example.goodfood.presentation.cart.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.goodfood.R
import com.example.goodfood.domain.model.CartItem
import com.example.goodfood.domain.model.MenuItem
import com.example.goodfood.presentation.details.components.MenuItemCardViewModel

@Composable
fun CartItemCard(
    cartItem: CartItem,
    viewModel: CartItemCardViewModel = hiltViewModel()
) {
    var state by remember { mutableStateOf(cartItem.noOfItems) }

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {


        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (cartItem.menuItem.isVegetarian) {
                Image(
                    modifier = Modifier.size(18.dp),
                    painter = painterResource(id = R.drawable.ic_veg),
                    contentDescription = "Végétarien"
                )
            } else {
                Image(
                    modifier = Modifier.size(16.dp),
                    painter = painterResource(id = R.drawable.ic_non_veg),
                    contentDescription = "Non-Végétarien²"
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = cartItem.menuItem.dish)

        }


        Column(

            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Surface(
                modifier = Modifier
                    .border(
                        BorderStroke(1.dp, Color.Black.copy(0.5f)),
                        MaterialTheme.shapes.medium
                    ),
                shape = MaterialTheme.shapes.medium,
                color = Color.White,
                contentColor = MaterialTheme.colors.primary
            ) {
                Row(
                    modifier = Modifier.padding(4.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        imageVector = Icons.Default.Remove,
                        contentDescription = stringResource(R.string.subtract),
                        modifier = Modifier
                            .padding(3.dp, 0.dp)
                            .size(20.dp)
                            .clickable {
                                if (state != 0) {
                                    viewModel.removeCartItem(cartItem.menuItem, 1)
                                    state--
                                }
                            }
                    )

                    Text(text = state.toString())

                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = stringResource(R.string.add),
                        modifier = Modifier
                            .padding(3.dp, 0.dp)
                            .size(20.dp)
                            .clickable {
                                state++
                                viewModel.addCartItem(cartItem.menuItem, 1)
                            }
                    )
                }

            }
        }


        Text(text = "  €  ${cartItem.menuItem.price}", overflow = TextOverflow.Ellipsis)


    }
}