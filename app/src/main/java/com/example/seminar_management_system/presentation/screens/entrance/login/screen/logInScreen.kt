package com.example.seminar_management_system.presentation.screens.login

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.seminar_management_system.R
import com.example.seminar_management_system.domain.model.loginmodel.LoginModel
import com.example.seminar_management_system.presentation.navigation.Screen
import com.example.seminar_management_system.presentation.screens.entrance.login.viewModel.LoginViewModel
import com.example.seminar_management_system.presentation.ui.theme.*
import com.example.seminar_management_system.util.toast
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch

@OptIn(
    ExperimentalComposeUiApi::class, ExperimentalCoroutinesApi::class,
    ExperimentalFoundationApi::class
)
@Composable
fun LoginPage(
    navController: NavController,
    viewModel: LoginViewModel = hiltViewModel()
) {

    val loginModel = remember {
        LoginModel("", "")
    }
    val context = LocalContext.current

    val scaffoldState = rememberScaffoldState()

    val scope = rememberCoroutineScope()

    val username = remember {
        mutableStateOf("")
    }

    val password = remember {
        mutableStateOf("")
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    val bringIntoViewRequester = BringIntoViewRequester()

    var passwordVisibility by remember {
        mutableStateOf(false)
    }

    val icon = if (passwordVisibility)
        painterResource(id = R.drawable.ic_visibility)
    else
        painterResource(id = R.drawable.ic_visibility_off)
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
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.amu_icon),
            contentDescription = "App Logo",
            modifier = Modifier.size(200.dp, 200.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = stringResource(R.string.login),
            style = aileronTypography.h1
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = stringResource(R.string.details_enter),
            style = poppinsTypography.h1
        )
        Spacer(modifier = Modifier.height(4.dp))

        OutlinedTextField(
            value = username.value,
            onValueChange = { username.value = it },
            label = {
                Text(
                    text = stringResource(R.string.name),
                    style = poppinsTypography.h1,
                    fontSize = 15.sp
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Grey,
                unfocusedBorderColor = Grey,
                focusedBorderColor = Green,
                cursorColor = Green,
                focusedLabelColor = Color.Black
            ),
            modifier = Modifier
                .height(56.dp)
                .onFocusEvent { event ->
                    if (event.isFocused) {
                        coroutineScope.launch {
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                },
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(

                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        Spacer(modifier = Modifier.height(22.dp))


        OutlinedTextField(
            value = password.value,
            onValueChange = { password.value = it },
            label = {
                Text(
                    text = stringResource(R.string.password),
                    style = poppinsTypography.h1,
                    fontSize = 15.sp
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Grey,
                unfocusedBorderColor = Grey,
                focusedBorderColor = Green,
                cursorColor = Green,
                focusedLabelColor = Color.Black
            ),
            modifier = Modifier
                .height(56.dp),
            trailingIcon = {

                IconButton(onClick = {

                    passwordVisibility = !passwordVisibility

                }) {

                    Icon(
                        painter = icon,
                        contentDescription = "Password İcon"
                    )

                }

            },

            visualTransformation = if (passwordVisibility) VisualTransformation.None
            else PasswordVisualTransformation(),
            shape = RoundedCornerShape(12.dp),
            singleLine = true,

            keyboardOptions = KeyboardOptions(

                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                    focusManager.clearFocus()
                    Log.e("LOG :::", username.value)
                    Log.e("LOG :::", password.value)
                    loginModel.username = username.value
                    loginModel.password = password.value
                    viewModel.loginUser(loginModel)
                    navController.popBackStack()
                    val state1 = viewModel.state.value
                    if (state1.success == true) {
                        navController.navigate(Screen.Home.route)
                    } else {
                        toast(context, "Invalid Username or password!!!")
                    }
                }
            )
        )
        Spacer(modifier = Modifier.height(22.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                modifier = Modifier.width(110.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Red,
                    contentColor = White
                ),
                shape = RoundedCornerShape(12.dp),
                onClick = {
                    navController.navigate(Screen.Signup.route)
                }
            ) {
                Text(
                    text = stringResource(R.string.signup),
                    style = dmsansTypography.h1,
                    fontSize = 15.sp
                )
            }
            Button(
                modifier = Modifier.width(110.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Red,
                    contentColor = White
                ),
                shape = RoundedCornerShape(12.dp),
                onClick = {
                    Log.e("LOG :::", username.value)
                    Log.e("LOG :::", password.value)
                    loginModel.username = username.value
                    loginModel.password = password.value
                    viewModel.loginUser(loginModel)
                    navController.popBackStack()
                    val state = viewModel.state.value
                    if (state.success == true) {
                        navController.navigate(Screen.Home.route)
                    } else {
                        toast(context, "Invalid Username or password!!!")
                    }


                }
            ) {
                Text(
                    text = stringResource(id = R.string.login),
                    style = dmsansTypography.h1,
                    fontSize = 15.sp
                )
            }
        }
    }
}