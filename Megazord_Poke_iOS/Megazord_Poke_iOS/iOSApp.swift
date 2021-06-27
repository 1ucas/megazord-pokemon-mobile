import SwiftUI
import Flutter

@main
struct iOSApp: App {
    
    static var flutterEngine = FlutterEngine(name: "my flutter engine")

	var body: some Scene {
		WindowGroup {
			ContentView()
                .onAppear {
                    iOSApp.flutterEngine.run()
                }
        }
	}
}
