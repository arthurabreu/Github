package com.arthur.github.network.api

import com.arthur.github.data.Name
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import javax.inject.Inject

class SampleApi
@Inject constructor(
    private val httpClient: HttpClient
) {
    suspend fun getRandomName(): Name =
        httpClient.get("https://random-data-api.com/api/name/random_name") {
            parameter("", "")
        }
}