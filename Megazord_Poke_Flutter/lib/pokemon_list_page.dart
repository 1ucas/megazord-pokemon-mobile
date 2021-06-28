
import 'package:flutter/material.dart';
import 'package:flutter/services.dart';

class PokemonListPage extends StatefulWidget {
  @override
  _PokemonListPageState createState() => _PokemonListPageState();
}

class _PokemonListPageState extends State<PokemonListPage> {

  static const platform = const MethodChannel('networking');
  List<String> _pokeList = [];

  @override
  void initState() {
    super.initState();
    listPokemons();
  }

  @override
  Widget build(BuildContext context) {
    return _buildPokemonList();
  }

  Widget _buildPokemonList() {
    return _pokeList.isNotEmpty
        ? ListView.builder(
        itemCount: _pokeList.length,
        itemBuilder: (BuildContext context, int position) {
          return Card(
            color: Colors.white,
            elevation: 2.0,
            child: Column(
              children: <Widget>[
                ListTile(
                  title: Text(this._pokeList[position])
                )
              ],
            ),
          );
        })
        : Center(child: Text("Vazio por agora."));
  }

  Future<void> listPokemons() async {
    try {
      final List<dynamic> result = await platform.invokeMethod('pokelist');
      final List<String> pokeresult = result.map((e) => e as String).toList();
      setState(() {
        _pokeList = pokeresult;
      });
    } on PlatformException catch (e) {}
  }
}
