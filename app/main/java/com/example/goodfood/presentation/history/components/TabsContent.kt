package com.example.goodfood.presentation.history.components

import androidx.compose.runtime.Composable
import com.example.goodfood.domain.model.Restaurant
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState

@ExperimentalPagerApi
@Composable
fun TabsContent(
    pagerState: PagerState,
    list: List<Restaurant>,
    onClick: (restaurant: Restaurant) -> Unit
) {

    HorizontalPager(count = 2, state = pagerState) { page ->
        when (page) {
            0 -> HistorySection()
            1 -> FavouritesSection(list = list, onClick)
        }
    }

}
