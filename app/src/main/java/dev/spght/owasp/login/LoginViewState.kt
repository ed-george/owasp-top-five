package dev.spght.owasp.login

sealed class LoginViewState {
    object Initial: LoginViewState()
    data class PinEntered(val pinCount: Int): LoginViewState()
    object PinCorrect: LoginViewState()
}
