package com.arttttt.archsample.domain.entity

import android.net.Uri
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BreedPicture(
    val pictureUri: Uri
) : Parcelable
