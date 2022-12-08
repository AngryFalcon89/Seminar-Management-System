package com.example.seminar_management_system.presentation.screens.entrance.splash.screen


import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.seminar_management_system.R
import com.example.seminar_management_system.domain.model.loginmodel.LoginModel
import com.example.seminar_management_system.presentation.navigation.Screen
import com.example.seminar_management_system.presentation.screens.entrance.splash.viewmodel.SplashViewModel
import com.example.seminar_management_system.presentation.ui.theme.*
import com.example.seminar_management_system.util.SpManager
import com.example.seminar_management_system.util.constants.SPLASH_SCREEN_DELAY
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    val systemBarColor = Red

    SideEffect {
        systemUiController.setStatusBarColor(
            color = systemBarColor
        )
        systemUiController.setNavigationBarColor(
            color = systemBarColor
        )
    }
    val context = LocalContext.current
    val loginModel = remember {
        LoginModel("", "")
    }
    loginModel.username = SpManager().getSharedPreference(context, SpManager.Sp.USERNAME, "Null").toString()
    loginModel.password = SpManager().getSharedPreference(context, SpManager.Sp.PASSWORD, "Null").toString()

    if (loginModel.username.trim().isNotEmpty() && loginModel.password.trim().isNotEmpty()) {
        LaunchedEffect(key1 = Unit) {
            viewModel.getLogin(loginModel)
        }

    }
    //Splash Screen animation
    var startAnimation by remember {
        mutableStateOf(false)
    }
    val offsetState by animateDpAsState(
        targetValue = if (startAnimation) -135.dp else 100.dp,
        animationSpec = tween(
            durationMillis = 2000
        )
    )
    val alphaState by animateFloatAsState(
        targetValue = if (startAnimation) 10f else 0f,
        animationSpec = tween(
            durationMillis = 1000
        )
    )
    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(SPLASH_SCREEN_DELAY)
        navController.popBackStack()
        if (viewModel.state.value.success == true) {
            navController.navigate(Screen.Home.route)
        } else {
            navController.navigate(Screen.Login.route)
        }
    }
    Splash(offsetState = offsetState, alphaState = alphaState)
}

@Composable
fun Splash(offsetState: Dp, alphaState: Float) {
    Box(
        modifier = Modifier
            .background(Brush.verticalGradient(listOf(White, White)))
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.amu_icon),
            contentDescription = stringResource(R.string.app_logo),
            Modifier
                .size(200.dp, 200.dp)
                .offset(y = offsetState)
                .alpha(alpha = alphaState)
        )
    }

}

@Preview(showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    Splash(offsetState = 0.dp, alphaState = 1f)
}
