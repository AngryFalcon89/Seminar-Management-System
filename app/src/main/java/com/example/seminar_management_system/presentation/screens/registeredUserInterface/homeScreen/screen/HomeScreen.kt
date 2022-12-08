package com.example.seminar_management_system.presentation.screens.registeredUserInterface.homeScreen.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.seminar_management_system.presentation.screens.registeredUserInterface.homeScreen.screen.BooksList
import com.example.seminar_management_system.presentation.screens.registeredUserInterface.homeScreen.screen.HomeTopBar
import com.example.seminar_management_system.presentation.screens.registeredUserInterface.homeScreen.viewmodel.HomeViewModel
import com.example.seminar_management_system.presentation.ui.theme.Red
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import me.onebone.toolbar.CollapsingToolbarScaffold
import me.onebone.toolbar.ScrollStrategy
import me.onebone.toolbar.rememberCollapsingToolbarScaffoldState

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel = hiltViewModel()
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
    LaunchedEffect(Unit, block = {viewModel.getBooks()})

    CollapsingToolbarScaffold(
        state = rememberCollapsingToolbarScaffoldState(),
        toolbar = { HomeTopBar() },
        modifier = Modifier.fillMaxSize(),
        scrollStrategy = ScrollStrategy.EnterAlways
    ) {
        if(viewModel.state.isNotEmpty()){
            val books = viewModel.state
            BooksList(books)
        }
        else{
        Column(
            modifier = Modifier.fillMaxSize().padding(0.dp,0.dp,0.dp,180.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = Red
            )
        }
        }
    }
}