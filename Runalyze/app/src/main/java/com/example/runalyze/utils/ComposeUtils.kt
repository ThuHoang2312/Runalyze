package com.example.runalyze.utils

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

object ComposeUtils {
    const val slideDownInDuration = 250
    const val slideDownOutDuration = 250

    //handle the enter and exit animations, making it easy to create slide-down effects for Composable elements.
    @Composable
    fun SlideDownAnimatedVisibility(
        modifier: Modifier = Modifier,
        inDurationInMillis: Int = slideDownInDuration,
        outDurationInMillis: Int = slideDownOutDuration,
        visible: Boolean,
        content: @Composable AnimatedVisibilityScope.() -> Unit
    ) {
        AnimatedVisibility(
            modifier = modifier,
            visible = visible,
            enter = slideInVertically(
                initialOffsetY = { fullHeight -> fullHeight },
                animationSpec = tween(
                    durationMillis = inDurationInMillis,
                    easing = LinearOutSlowInEasing
                )
            ),
            exit = slideOutVertically(
                targetOffsetY = { fullHeight -> fullHeight },
                animationSpec = tween(
                    durationMillis = outDurationInMillis,
                    easing = FastOutLinearInEasing
                )
            ),
            content = content
        )
    }
}