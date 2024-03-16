package com.mine.cappella.screens

import android.content.Context
import androidx.compose.material3.SnackbarHostState

suspend fun showSnackBar(
    snackBarValue: UiText?,
    snackBarHostState: SnackbarHostState,
    context: Context,
    onSnackBarShown: () -> Unit
) {
    snackBarValue?.let {
        snackBarHostState.showSnackbar(
            it.asString(context = context)
        )
        onSnackBarShown()
    }

}