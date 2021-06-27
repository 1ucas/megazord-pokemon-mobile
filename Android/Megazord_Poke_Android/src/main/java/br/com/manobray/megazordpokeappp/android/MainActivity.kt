package br.com.manobray.megazordpokeappp.android

import PokemonFacade
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import br.com.manobray.megazordpokeappp.Greeting
import android.widget.TextView
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.bumptech.glide.Glide
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import io.flutter.embedding.android.FlutterActivity


fun greet(): String {
    return Greeting().greeting()
}

class MainActivity : AppCompatActivity() {

    private val facade = PokemonFacade()
    private val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        setContent { // In here, we can call composables!
            MaterialTheme {
                Column {
                    Greeting(name = "compose")
                    Greeting(name = "compose")
                    Greeting(name = "compose")
                }
            }
        }
        //displayTopPokemon()
    }

    @Composable
    fun Greeting(name: String) {
        Text(text = "Hello $name!")
    }

    private fun displayTopPokemon() {
        mainScope.launch {
            kotlin.runCatching {
                facade.getPokemonTop()
            }.onSuccess {
                val tvName: TextView = findViewById(R.id.text_name)
                val ivPokemon: ImageView = findViewById(R.id.image_pokemon)
                tvName.text = it.species.name
                Glide.with(this@MainActivity).load(it.imageUrl).into(ivPokemon);
            }.onFailure {
                Log.e("ErrorImage", it.localizedMessage?:"")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }
}
