package com.arttttt.archsample.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Breed(
    val title: String
) : Parcelable
