package com.owl.bizcard.model

import androidx.compose.ui.graphics.painter.Painter

data class Portfolio(
    val activity : String,
    val date : String,
    val img : Painter
)