package com.example.seminar_management_system.presentation.screens.registeredUserInterface.homeScreen.screen

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.animation.core.Spring.DampingRatioHighBouncy
import androidx.compose.animation.core.Spring.DampingRatioLowBouncy
import androidx.compose.animation.core.Spring.StiffnessLow
import androidx.compose.animation.core.Spring.StiffnessVeryLow
import androidx.compose.foundation.gestures.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.PointerEvent
import androidx.compose.ui.input.pointer.PointerInputChange
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.seminar_management_system.presentation.screens.registeredUserInterface.homeScreen.bookstate.Books
import com.example.seminar_management_system.presentation.ui.theme.*
import kotlinx.coroutines.launch
import kotlin.math.roundToInt


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BooksList(
    books: List<Books>,
    modifier: Modifier = Modifier,
) {
    val offsetY = remember { androidx.compose.animation.core.Animatable(0f) }
    val scope = rememberCoroutineScope()

    val visibleState = remember {
        MutableTransitionState(false).apply {
            // Start the animation immediately.
            targetState = true
        }
    }

    // Fade in entry animation for the entire list
    AnimatedVisibility(
        visibleState = visibleState,
        enter = fadeIn(
            animationSpec = spring(dampingRatio = DampingRatioHighBouncy)
        ),
        exit = fadeOut()
    ) {
        LazyColumn(
            modifier = Modifier
                .offset { IntOffset(0, offsetY.value.roundToInt()) }
                .pointerInput(Unit) {
                    forEachGesture {
                        awaitPointerEventScope {
                            awaitFirstDown()
                            do {
                                val event: PointerEvent = awaitPointerEvent()
                                event.changes.forEach { pointerInputChange: PointerInputChange ->
                                    //Consume the change
                                    scope.launch {
                                        offsetY.snapTo(
                                            offsetY.value + pointerInputChange.positionChange().y
                                        )
                                    }
                                }
                            } while (event.changes.any { it.pressed })
                            scope.launch {
                                offsetY.animateTo(
                                    targetValue = 0f, spring(
                                        dampingRatio = Spring.DampingRatioLowBouncy,
                                        stiffness = StiffnessLow

                                    )
                                )
                            }
                        }
                    }
                }) {
            itemsIndexed(books) { index, book ->
                HeroListItem(
                    books = book,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        // Animate each list item to slide in vertically
                        .animateEnterExit(
                            enter = slideInVertically(
                                animationSpec = spring(
                                    stiffness = StiffnessVeryLow,
                                    dampingRatio = DampingRatioLowBouncy
                                ),
                                initialOffsetY = { it * (index + 1) } // staggered entrance
                            )
                        )
                )
            }
        }
    }
}

@Composable
fun HeroListItem(
    books: Books,
    modifier: Modifier = Modifier
) {
    val offsetY = remember { androidx.compose.animation.core.Animatable(0f) }
    val scope = rememberCoroutineScope()
    Card(
        elevation = 6.dp,
        modifier = modifier
            .offset { IntOffset(0, offsetY.value.roundToInt()) }
            .pointerInput(Unit) {
                forEachGesture {
                    awaitPointerEventScope {
                        awaitFirstDown()
                        do {
                            val event: PointerEvent = awaitPointerEvent()
                            event.changes.forEach { pointerInputChange: PointerInputChange ->
                                //Consume the change
                                scope.launch {
                                    offsetY.snapTo(
                                        offsetY.value + pointerInputChange.positionChange().y
                                    )
                                }
                            }
                        } while (event.changes.any { it.pressed })
                        scope.launch {
                            offsetY.animateTo(
                                targetValue = 0f, spring(
                                    dampingRatio = Spring.DampingRatioLowBouncy,
                                    stiffness = StiffnessLow

                                )
                            )
                        }
                    }
                }
            }
            .shadow(
                elevation = 8.dp,
                ambientColor = Red,
                spotColor = Red
            ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .sizeIn(minHeight = 72.dp)
        ) {
            Text(
                text = books.Title.toString(),
                style = dmsansTypography.h1,
                color = if (books.BookStatus == "1") {
                    Green
                } else {
                    Red
                }
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {if (books.Author.toString()!="null"&&books.Author.toString().trim().isNotEmpty()){
                Text(
                    text = books.Author.toString(),
                    style = dmsansTypography.h2,
                    color = if (books.BookStatus == "1") {
                        Green
                    } else {
                        Red
                    }
                )}
                if (books.Edition.toString()!="null"&&books.Edition.toString().trim().isNotEmpty()) {
                    Text(
                        text = "Edition: " + books.Edition.toString(),
                        style = dmsansTypography.h2,
                        color = if (books.BookStatus == "1") {
                            Green
                        } else {
                            Red
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (books.Publisher.toString()!="null") {
                    Text(
                        text = books.Publisher.toString(),
                        style = dmsansTypography.h2,
                        color = if (books.BookStatus == "1") {
                            Green
                        } else {
                            Red
                        }
                    )
                }
                if (books.PublishingYear.toString()!="null"&&books.PublishingYear.toString().trim().isNotEmpty()) {
                    Text(
                        text = "Publishing Year: " + books.PublishingYear,
                        style = dmsansTypography.h2,
                        color = if (books.BookStatus == "1") {
                            Green
                        } else {
                            Red
                        }
                    )
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (books.Id.toString()!="null") {
                    Text(
                        text = "Id: " + books.Id.toString(),
                        style = dmsansTypography.h2,
                        color = if (books.BookStatus == "1") {
                            Green
                        } else {
                            Red
                        }
                    )
                }
                if (books.AccessionNumber.toString()!="null"&&books.AccessionNumber.toString().trim().isNotEmpty()) {
                    Text(
                        text = "Acc. No.: " + books.AccessionNumber,
                        style = dmsansTypography.h2,
                        color = if (books.BookStatus == "1") {
                            Green
                        } else {
                            Red
                        }
                    )
                }
                if (books.MalAccNo.toString()!="null"&&books.MalAccNo.toString().trim().isNotEmpty()) {
                    Text(
                        text = "MalAcc. No.: " + books.MalAccNo,
                        style = dmsansTypography.h2,
                        color = if (books.BookStatus == "1") {
                            Green
                        } else {
                            Red
                        }
                    )
                }
            }
        }
    }
}