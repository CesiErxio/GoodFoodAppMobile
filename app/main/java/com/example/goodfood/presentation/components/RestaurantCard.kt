package com.example.goodfood.presentation.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.goodfood.R
import com.example.goodfood.domain.model.Restaurant
import com.example.goodfood.presentation.common.getTimeInMins

@Composable
fun RestaurantCard(
    restaurant: Restaurant,
    modifier: Modifier
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp, 0.dp)
    )
    {
        Row(
            modifier = modifier
                .fillMaxWidth()
                ,
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = restaurant.image),
                contentDescription = "Restaurant",
                modifier = Modifier
                    .size(130.dp, 170.dp)
                    .shadow(elevation = 0.dp, shape = RoundedCornerShape(8.dp), clip = true),
                contentScale = ContentScale.Crop
            )
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = restaurant.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                )
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = "Rating",
                        modifier = Modifier
                            .size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(2.dp))

                    Text(text = "${restaurant.rating}(${getCustomerInfo(restaurant.noOfRatings)}) ")
                    Text(
                        text = "·",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(text = restaurant.timeInMillis.getTimeInMins())
                }
                Text(
                    text = restaurant.variety,
                    modifier = Modifier.alpha(0.5f),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Text(
                    text = restaurant.place,
                    modifier = Modifier.alpha(0.5f),
                )
            }

        }
        Spacer(modifier = Modifier.height(18.dp))

    }
}

fun getCustomerInfo(noOfRatings: Int): String {
    val thresholds = listOf(50, 100, 150, 200, 250, 300, 350, 400, 450, 500, 550, 600, 650, 700, 750, 800)
    for (threshold in thresholds) {
        if (noOfRatings <= threshold) {
            return "${threshold}+"
        }
    }
    return "1000+"
}

