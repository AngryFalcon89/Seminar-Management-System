package com.example.seminar_management_system.presentation.screens.registeredUserInterface.homeScreen.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.seminar_management_system.R
import com.example.seminar_management_system.presentation.screens.registeredUserInterface.homeScreen.bookstate.Books
import com.example.seminar_management_system.presentation.ui.theme.*
import kotlinx.coroutines.launch
import me.onebone.toolbar.CollapsingToolbar
import me.onebone.toolbar.rememberCollapsingToolbarState


@Composable
fun HomeTopBar() {
    val focusManager = LocalFocusManager.current
    val bookSearch = remember {
        mutableStateOf("")
    }
    TopAppBar(
        modifier = Modifier.height(90.dp).shadow(
            elevation = 8.dp,
            ambientColor = Red,
            spotColor = Red
        ),
        title = {
            Column(
                modifier = Modifier.fillMaxHeight().padding(0.dp,0.dp,12.dp,0.dp)
            ) {
                Spacer(modifier = Modifier.height(5.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.amu_icon),
                        contentDescription = "Image",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(80.dp)
                    )
                }
//                Spacer(modifier = Modifier.height(10.dp))
//
//                Row(
//                    modifier = Modifier.fillMaxWidth(),
//                    horizontalArrangement = Arrangement.Center,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    OutlinedTextField(
//                        modifier = Modifier.height(56.dp),
//                        value = bookSearch.value,
//                        onValueChange = { bookSearch.value = it },
//                        label = { Text(text = "Search", style = poppinsTypography.h1, fontSize = 15.sp) },
//                        colors = TextFieldDefaults.outlinedTextFieldColors(
//                            backgroundColor = Grey,
//                            unfocusedBorderColor = Grey,
//                            focusedBorderColor = Green,
//                            cursorColor = Green,
//                            focusedLabelColor = Color.Black
//                        ),
//                        textStyle = dmsansTypography.h3,
//                        shape = RoundedCornerShape(12.dp),
//                        trailingIcon = {
//                            IconButton(onClick = { }) {
//                                Icon(imageVector = Icons.Filled.Search, contentDescription ="Search")
//                            }
//                        },
//                        singleLine = true,
//                        keyboardOptions = KeyboardOptions(
//                            keyboardType = KeyboardType.Email,
//                            imeAction = ImeAction.Done
//                        ),
//                        keyboardActions = KeyboardActions(
//                            onDone = {
//                                focusManager.clearFocus()
//                            }
//                        )
//                    )
//                }
            }
        },
        backgroundColor = Color.White,
        elevation = 12.dp,
    )
}


//@Preview(showSystemUi = true)
//@Composable
//fun PreviewTopBar() {
//    HomeTopBar()
//}


