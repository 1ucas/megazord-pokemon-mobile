//
//  FlutterWrapperView.swift
//  Megazord_Poke_iOS
//
//  Created by Lucas Ramos Maciel on 27/06/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import Flutter
import Megazord_Poke_KMM

struct FlutterWrapperView: UIViewControllerRepresentable {
    
    private static var pokeChannel:PokeMethodChannel?
    
    func makeUIViewController(context: Context) -> UIViewController {
        let flutterEngine = iOSApp.flutterEngine
        let flutterViewController =
            FlutterViewController(project: nil, initialRoute: "/pokelist", nibName: nil, bundle: nil)
        
        FlutterWrapperView.pokeChannel = PokeMethodChannel(flutterViewController.binaryMessenger)
        
        return flutterViewController
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) { }
    
    typealias UIViewControllerType = UIViewController
    
}

private class PokeMethodChannel {
    
    let facade = PokemonFacade()
    var pokeChannel: FlutterMethodChannel

    init(_ binaryMessenger: FlutterBinaryMessenger) {
        pokeChannel = FlutterMethodChannel(name: "networking", binaryMessenger: binaryMessenger)
        pokeChannel.setMethodCallHandler { [weak self] call, result in
          // Note: this method is invoked on the UI thread.
            guard call.method == "pokelist" else {
                result(FlutterMethodNotImplemented)
                return
            }
            self?.receivePokemonList { list in
                result(list)
            }
        }
    }
    
    private func receivePokemonList(completion: @escaping ([String]) -> Void) {
        facade.listPokemons { list, error in
            if let pokelist = list {
                completion(pokelist)
            } else {
                completion([])
            }
        }
    }
}
