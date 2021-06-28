package br.com.manobray.megazordpokeappp.network

import io.ktor.client.*
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json

internal class PokemonApi {
    private val httpClient = HttpClient {
        install(JsonFeature) {
            val json = Json { ignoreUnknownKeys = true }
            serializer = KotlinxSerializer(json)
        }
    }

    suspend fun getCharmander(): Pokemon {
        return httpClient.get(POKEMON_TOP_ENDPOINT)
    }

    suspend fun listPokemons(): PokeListResult {
        return httpClient.get(POKEMON_LIST_ENDPOINT)
    }

    companion object {
        private const val POKEMON_TOP_ENDPOINT = "https://pokeapi.co/api/v2/pokemon/4"
        private const val POKEMON_LIST_ENDPOINT = "https://pokeapi.co/api/v2/pokemon/?offset=0&limit=9"
    }
}