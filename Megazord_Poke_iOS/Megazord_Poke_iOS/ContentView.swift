import SwiftUI
import Megazord_Poke_KMM

struct ContentView: View {
    
    let facade = PokemonFacade()
    @State private var topPokemon: Pokemon?
    
    var body: some View {
        VStack {
            Text("Pokemon TOP!")
            .padding()
            
            AsyncImage(url: URL(string: topPokemon?.imageUrl ?? ""), content: { image in
                image.resizable()
                    .aspectRatio(contentMode: .fit)
                
            }, placeholder: {
                ProgressView()
            })
            .padding()
            
            Text(topPokemon?.species.name ?? "")
            .padding()
            
            Button("VER OUTROS") {
                
            }
            .padding()
            
            Spacer()
        }.onAppear {
            facade.getPokemonTop { pokemon, error in
                if let pokemon = pokemon {
                    topPokemon = pokemon
                }
            }
        }
    }
}
