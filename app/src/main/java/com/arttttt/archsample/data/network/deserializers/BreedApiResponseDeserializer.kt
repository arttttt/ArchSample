package com.arttttt.archsample.data.network.deserializers

import com.arttttt.archsample.data.network.model.BreedApiResponse
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class BreedApiResponseDeserializer : JsonDeserializer<BreedApiResponse?> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): BreedApiResponse? {
        return json
            ?.takeIf { it.isJsonObject }
            ?.asJsonObject
            ?.takeIf { it.has("message") }
            ?.get("message")
            ?.takeIf { it.isJsonObject }
            ?.asJsonObject
            ?.let { jsonObject ->
                BreedApiResponse(
                    message = BreedApiResponse.BreedApiModel(
                        breeds = jsonObject.entrySet().map { (key, _) -> key }
                    )
                )
            }
    }
}
