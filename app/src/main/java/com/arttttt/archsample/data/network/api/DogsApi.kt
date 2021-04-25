package com.arttttt.archsample.data.network.api

import com.arttttt.archsample.data.network.model.BreedApiResponse
import com.arttttt.archsample.data.network.model.BreedPicturesApiResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface DogsApi {

    @GET("breeds/list/all")
    fun getBreedList(): Single<BreedApiResponse>

    @GET("breed/{breed}/images")
    fun getBreedPictures(@Path("breed") breed: String): Single<BreedPicturesApiResponse>

}
