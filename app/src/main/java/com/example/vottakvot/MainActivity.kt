package com.example.vottakvot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.getValue
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.vottakvot.ViewModel.InquirerViewModel
import com.example.vottakvot.ViewModel.SplashViewModel
import com.example.vottakvot.ViewModel.WelcomeViewModel
import com.example.vottakvot.data.DataStoreRepository
import com.example.vottakvot.navigation.SetupNavGraph
import com.example.vottakvot.ui.theme.VotTakVotTheme
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var welcomeViewModel = WelcomeViewModel(DataStoreRepository(this))
        var inquirerViewModel = InquirerViewModel(DataStoreRepository(this))
        var splashViewModel: SplashViewModel = SplashViewModel(DataStoreRepository(context = this))
        installSplashScreen().setKeepOnScreenCondition {
            !splashViewModel.isLoading.value
        }
        //
        installSplashScreen().setKeepOnScreenCondition {
            // ещё не загружался экран загрузки
            !splashViewModel.isLoading.value
        }

        setContent {
            VotTakVotTheme {
                val screen by splashViewModel.startDestination
                val navController = rememberNavController()
                SetupNavGraph(navController = navController,
                    startDestination = screen,
                    welcomeViewModel = welcomeViewModel,
                    inquirerViewModel = inquirerViewModel)
            }
        }
    }
}

