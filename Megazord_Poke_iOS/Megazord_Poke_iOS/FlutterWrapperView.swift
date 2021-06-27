//
//  FlutterWrapperView.swift
//  Megazord_Poke_iOS
//
//  Created by Lucas Ramos Maciel on 27/06/21.
//  Copyright © 2021 orgName. All rights reserved.
//

import SwiftUI
import Flutter

struct FlutterWrapperView: UIViewControllerRepresentable {
    
    func makeUIViewController(context: Context) -> UIViewController {
        let flutterEngine = iOSApp.flutterEngine
        let flutterViewController =
            FlutterViewController(engine: flutterEngine, nibName: nil, bundle: nil)
        return flutterViewController
    }
    
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) { }
    
    typealias UIViewControllerType = UIViewController
    
   
}
