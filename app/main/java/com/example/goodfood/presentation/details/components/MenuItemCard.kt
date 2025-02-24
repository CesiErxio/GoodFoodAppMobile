package com.example.goodfood.presentation.details.components

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material.icons.filled.Star
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.goodfood.R
import com.example.goodfood.domain.model.MenuItem

@Composable
fun MenuItemCard(
    menuItem: MenuItem,
    viewModel: MenuItemCardViewModel = hiltViewModel()
    ) {
    var state by remember { mutableStateOf(0) }
    Log.e("MenuItem", menuItem.toString())
    Row(
        modifier = Modifier
            .height(120.dp)
            .fillMaxWidth()
            .padding(24.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (menuItem.isVegetarian) {
                    Image(
                        modifier = Modifier.size(18.dp),
                        painter = painterResource(id = R.drawable.ic_veg),
                        contentDescription = stringResource(R.string.vegetarian)
                    )
                } else {
                    Image(
                        modifier = Modifier.size(16.dp),
                        painter = painterResource(id = R.drawable.ic_non_veg),
                        contentDescription = stringResource(R.string.non_vegetarian)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = menuItem.dish)
            }

            Text(text = "  €  ${menuItem.price}", overflow = TextOverflow.Ellipsis)
            Row {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = stringResource(id = R.string.rating),
                    tint = Color(0xFFFF7A00)
                )
                Text(text = menuItem.rating.toString())
                Text(
                    text = " · ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "${menuItem.noOfRatings} avis",
                )
            }
        }

        if (
            state == 0
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            )
            {
                Row {
                    Text(
                        text = stringResource(R.string.add),
                        modifier = Modifier.clickable {
                            state++
                            viewModel.addCartItem(menuItem, state)
                        },
                        color = MaterialTheme.colors.primary,
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(24.dp))
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
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
                                    viewModel.removeCartItem(menuItem, 1)
                                    state--
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
                                    viewModel.addCartItem(menuItem, 1)
                                }
                        )


                    }

                }
            }
        }
    }
}
