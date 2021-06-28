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
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.manobray.megazordpokeappp.network.Pokemon
import br.com.manobray.megazordpokeappp.network.Species
import com.bumptech.glide.Glide
import com.google.accompanist.glide.rememberGlidePainter
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import io.flutter.embedding.android.FlutterActivity
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private val facade = PokemonFacade()
    private val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        setContent { // In here, we can call composables!
            MaterialTheme {
                PokemonScreen()
            }
        }
        //displayTopPokemon()
    }

    @Composable
    fun PokemonScreen() {
        val context = LocalContext.current

        val composableScope = rememberCoroutineScope()
        var pokemon: MutableState<Pokemon?> = remember {
            mutableStateOf(null)
        }

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            composableScope.launch {
                pokemon.value = facade.getPokemonTop()
            }

            Text(text = "Pokemon TOP")
            Image(
                painter = rememberGlidePainter(
                    request = pokemon.value?.imageUrl,
                    previewPlaceholder = R.drawable.abc_vector_test
                ),
                contentDescription = "",
                modifier = Modifier
                    .requiredWidth(200.dp)
                    .requiredHeight(300.dp)
            )
            Text(text = pokemon.value?.species?.name ?: "")
            Button(
                onClick = {
                    context.startActivity(FlutterActivity.createDefaultIntent(context))
                }, colors = ButtonDefaults.textButtonColors(
                    backgroundColor = Color.Blue
                )
            ) {
                Text("VER OUTROS", color = Color.White)
            }
        }
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
