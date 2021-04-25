package com.arttttt.archsample.data.network.model

import com.arttttt.archsample.data.network.deserializers.BreedApiResponseDeserializer
import com.google.gson.annotations.JsonAdapter

@JsonAdapter(BreedApiResponseDeserializer::class)
data class BreedApiResponse(
    val message: BreedApiModel
) {
    data class BreedApiModel(
        val breeds: List<String>
    )
}
