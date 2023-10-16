package com.example.vottakvot.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.vottakvot.R
import com.example.vottakvot.ViewModel.WelcomeViewModel
import com.example.vottakvot.navigation.Screen
import com.example.vottakvot.navigation.WelcomePage
import com.google.accompanist.pager.*


fun DataGeneration(welcomeViewModel: WelcomeViewModel)
{
    welcomeViewModel.createExamplePageList()
}
//  экраны приветствия
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun WelcomeScreen(
    //context: Context,
    navController: NavHostController,
    welcomeViewModel: WelcomeViewModel
) {
    // заполнение моками
    DataGeneration(welcomeViewModel)
    //  все страницы приветствия
    val pages = welcomeViewModel.getWelcomePagesList()
    if (pages.size == 0) {
        navController.navigate(Screen.Home.route)
        return
    }
    val pagerCount = pages.size
    val pagerState = rememberPagerState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(androidx.compose.material3.MaterialTheme.colorScheme.surface)
    ) {
        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
            //.weight(10f),
            count = pagerCount,
            state = pagerState,
            verticalAlignment = Alignment.Top,
        ) { position ->
            PagerScreen(
                onBoardingPage = pages[position]
            )
        }

        BottomNavigation(
            modifier = Modifier.
            wrapContentHeight(),
            navController = navController,
            pagerState = pagerState,
            pagerCount = pagerCount
        )

    }
}

@Composable
fun PagerScreen(onBoardingPage: WelcomePage) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
        ,
      //  horizontalAlignment = Alignment.Horizontal { size, space, layoutDirection ->  },
        verticalArrangement = Arrangement.Top
    ) {
        ImageAndText(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(
                    top = 20.dp
                ),
            painter = painterResource(id = onBoardingPage.image),
            ico = painterResource(id = R.drawable.logo),
            contentDescription = "Pager Image",
            title = "ВотТакВот"
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(
                    top = 20.dp
                ),
            color = androidx.compose.material3.MaterialTheme.colorScheme.onBackground,
            text = onBoardingPage.title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(
                    top = 20.dp
                ),

            text = onBoardingPage.description,
            color = androidx.compose.material3.MaterialTheme.colorScheme.onBackground,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Justify
        )
    }
}

@Composable
fun ImageAndText(
    modifier: Modifier = Modifier,
    painter: Painter,
    ico : Painter,
    contentDescription: String,
    title: String
) {
        var sizeImage by remember { mutableStateOf(IntSize.Zero) }

        val gradient = Brush.verticalGradient(
            colors = listOf(Color.Transparent, androidx.compose.material3.MaterialTheme.colorScheme.background),
            startY = sizeImage.height.toFloat()/2,  // 1/3
            endY = sizeImage.height.toFloat()/1
        )

        Box(){
            Image(painter = painter,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .fillMaxHeight(0.5f)
                    .onGloballyPositioned {
                    sizeImage = it.size
                })
            Box(modifier = Modifier
                .matchParentSize().background(gradient))       {
            Image(
                painter = ico,
                contentDescription = contentDescription,
                modifier = Modifier
            )

            Text(
                text = title,
                fontSize = 36.sp,
                fontFamily = FontFamily.Cursive,
                color = androidx.compose.material3.MaterialTheme.colorScheme.background,
                modifier = Modifier
                   .padding(top = 5.dp)


            )
        }
        }
        Box(
            modifier = Modifier
                //.align(Alignment.BottomCenter)
        )
        {
            val gradient = Brush.verticalGradient(
                colors = listOf(Color.Transparent, androidx.compose.material3.MaterialTheme.colorScheme.background),
                startY = 0.5f,  // 1/3
                endY = 1f
            )
        Row (
            modifier = Modifier
                //.padding(top = 10.dp)
                .fillMaxWidth(1f)
                .fillMaxHeight(0.25f),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.Bottom

        ) {}


    }
}


@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
@Composable
fun BottomNavigation(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    pagerState: PagerState,
    pagerCount: Int
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
        //.fillMaxHeight(),
        contentAlignment = BottomCenter

    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth(1f)
                .padding(bottom = 0.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Bottom
        )
        {
            SkipButton(
                modifier = Modifier
                    .fillMaxWidth(1f),
                pagerState = pagerState
            ) {
                //welcomeViewModel.saveOnBoardingState(completed = true)
                navController.popBackStack()
                navController.navigate(Screen.Home.route)
            }
            HorizontalPagerIndicator(
                activeColor = androidx.compose.material3.MaterialTheme.colorScheme.primary,
                inactiveColor = Color(R.color.inactive),
                pagerState = pagerState
            )
            ContinueButton(
                modifier = Modifier
                    .fillMaxWidth(1f),
                pagerState = pagerState,
                text = "Да, конечно!",
                pagerCount = pagerCount
            ) {
                //welcomeViewModel.saveOnBoardingState(completed = true)
                navController.popBackStack()
                navController.navigate(Screen.Home.route)
            }
        }
    }




}


@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun SkipButton(
    modifier: Modifier,
    pagerState: PagerState,
    text: String = "Не сейчас",
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Start
    ) {
        AnimatedVisibility(
            modifier = Modifier.wrapContentHeight(),
            visible = pagerState.currentPage >= 0
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(

                    contentColor = androidx.compose.material3.MaterialTheme.colorScheme.onSecondaryContainer
                ),

                    //backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.background,
            border = BorderStroke(0.dp, Color.White)
            ) {
                Text(text)
            }
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun ContinueButton(
    modifier: Modifier,
    pagerState: PagerState,
    pagerCount: Int,
    text: String = "Да, конечно!",
    onClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.End
    ) {
        AnimatedVisibility(
            modifier = Modifier.wrapContentHeight(),
            visible = pagerState.currentPage >= 0
        ) {
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(

                    contentColor = androidx.compose.material3.MaterialTheme.colorScheme.onSecondaryContainer,
                    backgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.background
                )
            ) {
                var _text = text
                if (pagerState.currentPage != pagerCount-1)
                    _text = "Продолжить..."
                Text(_text)
            }
        }
    }
}