import br.com.manobray.megazordpokeappp.network.Pokemon
import br.com.manobray.megazordpokeappp.network.PokemonApi

class PokemonFacade {
    private val api = PokemonApi()

    @Throws(Exception::class) suspend fun getPokemonTop() : Pokemon {
        return api.getCharmander()
    }

}