package br.com.manobray.megazordpokeappp

class Greeting {
    fun greeting(): String {
        return "Hello, ${Platform().platform}!"
    }
}