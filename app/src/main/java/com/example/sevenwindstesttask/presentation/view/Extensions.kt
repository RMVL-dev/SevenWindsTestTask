package com.example.sevenwindstesttask.presentation.view

import android.view.View
import com.example.sevenwindstesttask.R
import com.google.android.material.snackbar.Snackbar

fun View.settingSnackBar(message:String, colorId:Int): Snackbar =
    Snackbar
        .make(this, message, Snackbar.LENGTH_LONG)
        .setBackgroundTint(context.resources.getColor(colorId))
        .setTextColor(context.resources.getColor(R.color.white))