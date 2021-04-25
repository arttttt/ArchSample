package com.arttttt.archsample.data.repository

import androidx.core.net.toUri
import com.arttttt.archsample.data.network.api.DogsApi
import com.arttttt.archsample.domain.entity.Breed
import com.arttttt.archsample.domain.entity.BreedPicture
import com.arttttt.archsample.domain.repository.DogsRepository
import io.reactivex.Single
import javax.inject.Inject

class DogsRepositoryImpl @Inject constructor(
    private val dogsApi: DogsApi
) : DogsRepository {

    override fun loadBreeds(): Single<List<Breed>> {
        return dogsApi
            .getBreedList()
            .map { response ->
                response.message.breeds.map { breed ->
                    Breed(
                        title = breed
                    )
                }
            }
    }

    override fun loadBreedPictures(breed: Breed): Single<List<BreedPicture>> {
        return dogsApi
            .getBreedPictures(breed.title)
            .map { response ->
                response.message.map { BreedPicture(it.toUri()) }
            }
    }
}
