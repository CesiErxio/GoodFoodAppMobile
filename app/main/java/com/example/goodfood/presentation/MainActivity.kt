package com.example.goodfood.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.goodfood.presentation.common.SplashViewModel
import com.example.goodfood.presentation.util.SetupNavigation
import com.example.goodfood.ui.theme.GoodFoodTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var splashViewModel: SplashViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen().setKeepOnScreenCondition {
            splashViewModel.isLoading.value
        }

        setContent {
            GoodFoodTheme {
                val screen by splashViewModel.startDestination

                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    SetupNavigation(
                        startDestination = screen
                    )
                }
            }
        }
    }
}


