package com.example.goodfood.presentation.history

import android.app.Activity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.goodfood.presentation.components.SearchBar
import com.example.goodfood.presentation.history.components.Tabs
import com.example.goodfood.presentation.history.components.TabsContent
import com.example.goodfood.presentation.util.Screen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@Composable
fun History(
    viewModel: HistoryViewModel = hiltViewModel(),
    navHostController: NavHostController
) {

    val state by viewModel.likedRestaurants


    val context = LocalContext.current as Activity

    val pagerState = rememberPagerState()


    context.window.statusBarColor = Color.Gray.toArgb()
    context.window.navigationBarColor = Color.White.toArgb()

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            SearchBar()
            Spacer(modifier = Modifier.height(8.dp))

            Tabs(pagerState = pagerState)
        }
        TabsContent(
            pagerState = pagerState, state.likedRestaurantList,
            onClick = {
                viewModel.onEvent(HistoryEvent.SelectRestaurant(it){
                    navHostController.navigate(Screen.RestaurantDetails.route)
                })
            }
        )

    }
}





