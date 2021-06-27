package br.com.manobray.megazordpokeappp.network

import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

class PokemonApi {
    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
    }

    suspend fun getCharmander(): Pokemon {
        return httpClient.get(POKEMONS_ENDPOINT)
    }

    companion object {
        private const val POKEMONS_ENDPOINT = "https://pokeapi.co/api/v2/pokemon/4"
    }
}