package com.example.goodfood.presentation.cart.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.unit.dp
import com.example.goodfood.domain.model.CartItem
import com.google.accompanist.flowlayout.FlowColumn

@Composable
fun ItemSection(
    list: List<CartItem>,
    onIncreaseClick: (cartItem: CartItem) -> Unit,
    onDecreaseClick: (cartItem: CartItem) -> Unit

) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {

        Card(
            shape = RoundedCornerShape(24.dp),
            elevation = 16.dp
        ) {
            Column(Modifier.padding(16.dp)) {
                if (list.isNotEmpty()) {
                    FlowColumn(
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        list.forEach {

                            CartItemCard(
                                cartItem = it
                            )
                        }

                    }
                } else {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                    ) {
                        Text(text = "Vide")
                    }
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 8.dp)
                )

                val inputValue = remember { mutableStateOf("") }
                val hintState = remember {
                    mutableStateOf(true)
                }

                Box {
                    BasicTextField(
                        value = inputValue.value,
                        onValueChange = { inputValue.value = it },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(24.dp)
                            .onFocusChanged {
                                if (inputValue.value == "") {
                                    hintState.value = !it.isFocused
                                }
                            }
                    )

                    if (hintState.value) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Ajouter une inscription au restaurateur si besoin...",
                                modifier = Modifier.alpha(0.5f),
                            )
                            Icon(
                                imageVector = Icons.Outlined.Edit,
                                contentDescription = "Retour",
                                modifier = Modifier.alpha(0.5f),
                            )

                        }
                    }
                }
            }
        }

    }
}
