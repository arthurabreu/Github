package com.arthur.github.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Name(
    val name: String,
    @SerialName("four_word_name") val fourWordName: String,
    @SerialName("last_name") val lastName: String
)
