package dev.spght.owasp.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.spght.owasp.login.LoginViewState.Initial
import dev.spght.owasp.login.LoginViewState.PinCorrect
import dev.spght.owasp.login.LoginViewState.PinEntered
import dev.spght.owasp.login.domain.LoginRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginRepository: LoginRepository
): ViewModel() {

    private val _event = MutableStateFlow<LoginViewState>(Initial)
    val event: StateFlow<LoginViewState> = _event.asStateFlow()

    private val userInputs: MutableList<Int> = mutableListOf()

    fun didSelect(value: Int) {
        // Don't do this IRL :)
        viewModelScope.launch {
            userInputs.add(value)
            _event.emit(PinEntered(userInputs.size % (PIN_SIZE + 1)))
            val enteredPin = userInputs.takeLast(PIN_SIZE)
            if (enteredPin == EXPECTED_PIN) {
                // DO NOT DO THIS IRL!
                loginRepository.saveUserPin(enteredPin)
                // Bingo!
                delay(200)
                _event.emit(PinCorrect)
            }
        }
    }

    companion object {
        // M4: Insecure Authentication
        // A PIN size of 4 is easily guessable
        // Most people use dates or common patterns
        private val PIN_SIZE = 4
        private val EXPECTED_PIN = (1..PIN_SIZE).toList()
    }
}