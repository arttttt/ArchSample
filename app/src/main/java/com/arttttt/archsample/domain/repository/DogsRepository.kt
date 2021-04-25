package com.arttttt.archsample.domain.repository

import com.arttttt.archsample.domain.entity.Breed
import com.arttttt.archsample.domain.entity.BreedPicture
import io.reactivex.Single

interface DogsRepository {
    fun loadBreeds(): Single<List<Breed>>
    fun loadBreedPictures(breed: Breed): Single<List<BreedPicture>>
}
