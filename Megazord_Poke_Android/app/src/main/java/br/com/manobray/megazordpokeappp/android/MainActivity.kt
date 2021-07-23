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
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.embedding.engine.dart.DartExecutor
import io.flutter.plugin.common.MethodChannel
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private val facade = PokemonFacade()
    private val mainScope = MainScope()
    private val POKECHANNEL = "networking"
    private lateinit var channel: MethodChannel
    lateinit var flutterEngine : FlutterEngine


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        flutterEngine = FlutterEngine(this)
        flutterEngine.navigationChannel.setInitialRoute("/pokelist");
        flutterEngine.dartExecutor.executeDartEntrypoint(
            DartExecutor.DartEntrypoint.createDefault()
        )

        FlutterEngineCache
            .getInstance()
            .put("my_engine_id", flutterEngine)

        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "networking").setMethodCallHandler {
                call, result ->
            if (call.method == "pokelist") {
                runBlocking {
                    val pokemonNames = facade.listPokemons()
                    result.success(pokemonNames)
                }
            } else {
                result.notImplemented()
            }
        }
        setContent {
            MaterialTheme {
                PokemonScreen()
            }
        }
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
                    val flutterActivity = FlutterActivity
                        .withCachedEngine("my_engine_id")
                        .build(context)
                    context.startActivity(flutterActivity)
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
