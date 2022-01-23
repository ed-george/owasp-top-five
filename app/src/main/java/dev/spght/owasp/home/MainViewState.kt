package dev.spght.owasp.home

sealed class MainViewState {
    object Loading: MainViewState()
    data class Home(val userGreeting: String): MainViewState()
}
