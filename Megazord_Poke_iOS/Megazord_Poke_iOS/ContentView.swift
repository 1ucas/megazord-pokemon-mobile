import SwiftUI
import Megazord_Poke_KMM

struct ContentView: View {
    
    let facade = PokemonFacade()
    @State private var topPokemon: Pokemon?
    @State var isLinkActive = false
    
    var body: some View {
        NavigationView {
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
                
                NavigationLink(destination: FlutterWrapperView().navigationBarTitle("Pokemons", displayMode: .inline), isActive: $isLinkActive) {
                    Button("VER OUTROS") {
                        self.isLinkActive = true
                    }
                    .padding()
                }
                
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
}
