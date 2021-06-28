package br.com.manobray.megazordpokeappp.network

import kotlinx.serialization.Serializable

@Serializable
data class PokeListResult (
    val count: Long,
    val next: String,
    val results: List<ResultName>
)

@Serializable
data class ResultName (
    val name: String
)