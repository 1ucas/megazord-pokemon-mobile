package br.com.manobray.megazordpokeappp.network

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pokemon(
    @SerialName("species")
    val species: Species,
    @SerialName("sprites")
    val sprites: Sprites
) {
    val imageUrl = sprites.other.artwork.imageUrl
}

@Serializable
data class Species(
    @SerialName("name")
    val name: String
)

@Serializable
data class Sprites(
    @SerialName("other")
    val other: Other
)

@Serializable
data class Other(
    @SerialName("official-artwork")
    val artwork: ArtWork
)

@Serializable
data class ArtWork(
    @SerialName("front_default")
    val imageUrl: String
)