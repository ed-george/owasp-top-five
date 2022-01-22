package dev.spght.owasp.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.spght.owasp.login.LoginViewState.Initial
import dev.spght.owasp.login.LoginViewState.PinCorrect
import dev.spght.owasp.login.LoginViewState.PinEntered
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(): ViewModel() {

    private val _event = MutableStateFlow<LoginViewState>(Initial)
    val event: StateFlow<LoginViewState> = _event.asStateFlow()

    private val userInputs: MutableList<Int> = mutableListOf()

    fun didSelect(value: Int) {
        // Don't do this IRL :)
        viewModelScope.launch {
            userInputs.add(value)
            _event.emit(PinEntered(userInputs.size % 5))
            if (userInputs.takeLast(4) == EXPECTED_PIN) {
                // Bingo!
                delay(200)
                _event.emit(PinCorrect)
            }
        }
    }

    companion object {
        // Very Secure. Much Security. WOW!
        private val EXPECTED_PIN = listOf(1,2,3,4)
    }
}