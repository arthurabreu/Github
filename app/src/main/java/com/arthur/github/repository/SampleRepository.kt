package com.arthur.github.repository

import com.arthur.github.network.api.SampleApi
import com.arthur.github.network.safeApiCall
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class SampleRepository
@Inject constructor(
    private val sampleApi: SampleApi
) {
    suspend fun getName() = safeApiCall(context = Dispatchers.IO) { sampleApi.getRandomName() }
}