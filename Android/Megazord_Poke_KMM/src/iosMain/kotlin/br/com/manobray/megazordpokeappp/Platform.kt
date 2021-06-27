package br.com.manobray.megazordpokeappp

import platform.UIKit.UIDevice

actual class Platform actual constructor() {
    actual val platform: String = "From " + UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
}