package com.example.goodfood.presentation.history.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.goodfood.R

@Composable
fun HistorySection() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Card(
            elevation = 16.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column() {
                        Text(text = "Fish n Rolls", fontWeight = FontWeight.Bold)
                        Text(
                            text = "Tezpur",
                            modifier = Modifier.alpha(0.5f),
                        )

                        Text(
                            text = "13 Aug 2022, 11:12 PM",
                            modifier = Modifier.alpha(0.5f),
                        )

                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                    ) {
                        Text(
                            text = "€7.90",
                        )
                    }
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 8.dp),
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(id = R.drawable.ic_non_veg),
                            contentDescription = "Non-Vegetarian"
                        )
                        Spacer(modifier = Modifier.width(8.dp))

                        Text(text = "Chinese Shawarma\nCombo (1)", maxLines = 2)
                    }
                    Row {
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Recommander")
                        }
                    }

                }

                Spacer(modifier = Modifier.height(8.dp))


                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = "Votre évaluation pour la livraison",
                                modifier = Modifier.alpha(0.5f),
                            )
                            Text(
                                text = "Votre évaluation pour la nourriture",
                                modifier = Modifier.alpha(0.5f),
                            )

                        }

                        Column {
                            Row {
                                Icon(
                                    imageVector = Icons.Filled.Star,
                                    contentDescription = "Rating",
                                    tint = Color(0xFFFF7A00)
                                )
                                Text(text = "5")
                            }
                            Row {
                                Icon(
                                    imageVector = Icons.Filled.Star,
                                    contentDescription = "Rating",
                                    tint = Color(0xFFFF7A00)
                                )
                                Text(text = "5")
                            }
                        }

                    }




                    Spacer(modifier = Modifier.width(16.dp))


                    Row() {
                        Text(
                            text = "Livrée",
                            modifier = Modifier.alpha(0.5f),
                        )
                        Icon(
                            imageVector = Icons.Filled.Circle,
                            contentDescription = "Rating",
                            tint = MaterialTheme.colors.primary
                        )

                    }
                }
            }

        }
        Spacer(modifier = Modifier.height(8.dp))
        Card(
            elevation = 16.dp
        ) {
            Column(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column() {
                        Text(text = "Fish n Rolls", fontWeight = FontWeight.Bold)
                        Text(
                            text = "Tezpur",
                            modifier = Modifier.alpha(0.5f),
                        )

                        Text(
                            text = "13 Aug 2022, 11:12 PM",
                            modifier = Modifier.alpha(0.5f),
                        )

                    }
                    Column(
                        horizontalAlignment = Alignment.End,
                    ) {
                        Text(
                            text = "€7.90",
                        )
                    }
                }
                Divider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(0.dp, 8.dp),
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            modifier = Modifier.size(16.dp),
                            painter = painterResource(id = R.drawable.ic_non_veg),
                            contentDescription = "Non-Vegetarian"
                        )
                        Spacer(modifier = Modifier.width(8.dp))

                        Text(text = "Chinese Shawarma\nCombo (1)", maxLines = 2)
                    }
                    Row {
                        Button(onClick = { /*TODO*/ }) {
                            Text(text = "Recommander")
                        }
                    }

                }

                Spacer(modifier = Modifier.height(8.dp))


                Row(
                    verticalAlignment = Alignment.Bottom,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Row {
                        Text(
                            text = "Noter la commande",
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.primary,
                            fontSize = 16.sp,
                            modifier = Modifier.clickable { }
                        )
                    }




                    Spacer(modifier = Modifier.width(16.dp))


                    Row() {
                        Text(
                            text = "Livrée",
                            modifier = Modifier.alpha(0.5f),
                        )
                        Icon(
                            imageVector = Icons.Filled.Circle,
                            contentDescription = "Avis",
                            tint = MaterialTheme.colors.primary
                        )

                    }
                }
            }

        }

    }
}
