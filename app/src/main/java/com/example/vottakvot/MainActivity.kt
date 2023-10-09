package com.example.vottakvot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.vottakvot.ViewModel.InquirerViewModel
import com.example.vottakvot.ViewModel.SplashViewModel
import com.example.vottakvot.ViewModel.WelcomeViewModel
import com.example.vottakvot.navigation.SetupNavGraph
import com.example.vottakvot.ui.theme.VotTakVotTheme
import com.google.accompanist.pager.ExperimentalPagerApi

import javax.inject.Inject


@ExperimentalAnimationApi
class MainActivity : ComponentActivity() {

    // экран-заставка
    // Splash API
    // implementation("androidx.core:core-splashscreen:1.0.0-beta01")
    @Inject
    lateinit var splashViewModel: SplashViewModel
    private val welcomeViewModel by viewModels<WelcomeViewModel>()
    private val inquirerViewModel by viewModels<InquirerViewModel>()
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //
        installSplashScreen().setKeepOnScreenCondition {
            // ещё не загружался экран загрузки
            !splashViewModel.isLoading.value
        }

        setContent {
            VotTakVotTheme {
                val screen by splashViewModel.startDestination
                val navController = rememberNavController()
                SetupNavGraph(navController = navController, startDestination = screen, welcomeViewModel = welcomeViewModel, inquirerViewModel = inquirerViewModel)
            }
        }
    }
}

