package com.example.seminar_management_system.presentation.screens.entrance.signin.screen

import android.content.Intent
import android.provider.Settings
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
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
import com.example.seminar_management_system.presentation.screens.entrance.signin.viewModel.SigninViewModel
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
fun SignupPage(
    navController: NavController,
    viewModel: SigninViewModel = hiltViewModel()
) {

    val state = viewModel.state.value
    val loginModel = remember {
        LoginModel("", "")
    }
    val context = LocalContext.current

    val intent = remember {
        Intent(Settings.ACTION_WIRELESS_SETTINGS)
    }

    val scaffoldState = rememberScaffoldState()

    val scope = rememberCoroutineScope()

    val username = remember {
        mutableStateOf("")
    }

    val password = remember {
        mutableStateOf("")
    }
    val confirmpassword = remember {
        mutableStateOf("")
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    val coroutineScope = rememberCoroutineScope()
    val focusManager = LocalFocusManager.current
    val bringIntoViewRequester = BringIntoViewRequester()

    var passwordVisibility by remember {
        mutableStateOf(false)
    }
    var confirmpasswordVisibility by remember {
        mutableStateOf(false)
    }
    val icon = if (passwordVisibility)
        painterResource(id = R.drawable.ic_visibility)
    else
        painterResource(id = R.drawable.ic_visibility_off)
    val confirmicon = if (confirmpasswordVisibility)
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
            text = "Admin Sign up",
            style = aileronTypography.h1
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = "Enter credential to sign up",
            style = poppinsTypography.h1
        )
        Spacer(modifier = Modifier.height(4.dp))
        var nameFieldState by remember { mutableStateOf("") }

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

        var passwordFieldState by remember { mutableStateOf("") }

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
                .height(56.dp)
                .onFocusEvent { event ->
                    if (event.isFocused) {
                        coroutineScope.launch {
                            bringIntoViewRequester.bringIntoView()
                        }
                    }
                }, trailingIcon = {

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
                imeAction = ImeAction.Next,
            )
        )
        Spacer(modifier = Modifier.height(22.dp))

        OutlinedTextField(
            value = confirmpassword.value,
            onValueChange = { confirmpassword.value = it },
            label = {
                Text(
                    text = "CONFIRM PASSWORD",
                    style = poppinsTypography.h1,
                    fontSize = 15.sp
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                backgroundColor = Grey,
                unfocusedBorderColor = Grey,
                focusedBorderColor = if (password.value == confirmpassword.value) {
                    Green
                } else {
                    Red
                },
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
                }, trailingIcon = {

                IconButton(onClick = {

                    confirmpasswordVisibility = !confirmpasswordVisibility

                }) {

                    Icon(
                        painter = confirmicon,
                        contentDescription = "Password İcon"
                    )

                }

            },

            visualTransformation = if (confirmpasswordVisibility) VisualTransformation.None
            else PasswordVisualTransformation(),
            shape = RoundedCornerShape(12.dp),
            singleLine = true,

            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (password.value != confirmpassword.value) {
                        toast(context, "Confirm password must be same as password")
                    } else {
                        keyboardController?.hide()
                        focusManager.clearFocus()
                        Log.e("LOG :::", username.value)
                        Log.e("LOG :::", password.value)
                        Log.e("LOG :::", confirmpassword.value)
                        loginModel.username = username.value
                        loginModel.password = password.value
                        viewModel.UserSignIn(loginModel)
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
                    navController.popBackStack()
                    navController.navigate(Screen.Login.route)
                }
            ) {
                Text(text = "Login", style = dmsansTypography.h1, fontSize = 15.sp)
            }
            Button(
                modifier = Modifier
                    .width(110.dp)
                    .bringIntoViewRequester(bringIntoViewRequester),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Red,
                    contentColor = White
                ),
                shape = RoundedCornerShape(12.dp),
                onClick = {
                    if (password.value != confirmpassword.value) {
                        toast(context, "Confirm password must be same as password")
                    } else {
                        Log.e("LOG :::", username.value)
                        Log.e("LOG :::", password.value)
                        loginModel.username = username.value
                        loginModel.password = password.value
                        navController.popBackStack()
                        viewModel.UserSignIn(loginModel)
                        if (state.success == true) {
                            navController.navigate(Screen.Login.route)
                        }
                    }
                }
            ) {
                Text(text = "Signup", style = dmsansTypography.h1, fontSize = 15.sp)
            }
        }
    }
}
