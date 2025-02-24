package com.example.goodfood.presentation.home

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.goodfood.R
import com.example.goodfood.presentation.components.RestaurantCard
import com.example.goodfood.presentation.components.SearchBar
import com.example.goodfood.presentation.home.components.AdSection
import com.example.goodfood.presentation.home.components.ChipBar
import com.example.goodfood.presentation.home.components.FavouriteSection
import com.example.goodfood.presentation.home.components.GreetingSection
import com.example.goodfood.presentation.home.components.MainSection
import com.example.goodfood.presentation.home.components.TopSection
import com.example.goodfood.presentation.util.Screen


@Composable
fun Home(
    scrollState: LazyListState,
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val context = LocalContext.current as Activity
    val firstName by viewModel.firstName.collectAsState(initial = "")
    val userType by viewModel.userType.collectAsState(initial = "")

    context.window.statusBarColor = Color.Gray.toArgb()
    context.window.navigationBarColor = Color.White.toArgb()

    val homeScreenState by viewModel.homeScreenState


    LazyColumn(
        modifier =
        Modifier
            .fillMaxWidth(),
        state = scrollState,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
            TopSection(navController = navController)
            Spacer(modifier = Modifier.height(8.dp))
        }
        item {
            GreetingSection(firstName)
            Spacer(modifier = Modifier.height(24.dp))
        }
        item {
            Column(
                modifier = Modifier.padding(8.dp, 0.dp)
            ) {
                SearchBar()
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        item {
            AdSection(homeScreenState.adsList)
            Spacer(modifier = Modifier.height(16.dp))
        }
        if (userType == "deliveryMan") {
            item{
                Column(modifier = Modifier.padding(8.dp, 0.dp))
                {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = stringResource(R.string.deliveryman),
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Light
                    )
                    Spacer(modifier = Modifier.height(8.dp))

                }
            }
        } else {
            if (homeScreenState.likedRestaurantList.isNotEmpty()) {
                item {
                    FavouriteSection(homeScreenState.likedRestaurantList) {
                        viewModel.onEvent(HomeScreenEvent.SelectRestaurant(it) {
                            navController.navigate(Screen.RestaurantDetails.route)
                        })
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
            item {
                Spacer(modifier = Modifier.height(8.dp))
                ChipBar()
            }
            item {
                MainSection()
            }
            items(homeScreenState.restaurantList.size) {
                RestaurantCard(
                    restaurant = homeScreenState.restaurantList[it],
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .clickable {
                            viewModel.onEvent(HomeScreenEvent.SelectRestaurant(homeScreenState.restaurantList[it]) {
                                navController.navigate(Screen.RestaurantDetails.route)
                            })
                        }
                )
            }
        }
    }
}











