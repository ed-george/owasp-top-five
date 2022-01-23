package dev.spght.owasp.home

import android.util.Base64
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.spght.owasp.home.MainViewState.Loading
import dev.spght.owasp.home.domain.HomeRepository
import dev.spght.owasp.login.domain.LoginRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val loginRepository: LoginRepository,
    private val homeRepository: HomeRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<MainViewState>(Loading)
    val uiState: StateFlow<MainViewState> = _uiState.asStateFlow()

    fun start() {
        viewModelScope.launch {

            // Cool new feature - get greeted by a random Rick & Morty character!
            val character = homeRepository.fetchCharacters().randomOrNull()

            // M5: Insecure Cryptography
            // Ensure you know the difference between encoding, hashing and encryption
            // Encoding: The process of converting data from one form factor to another (two-way operation)
            // Hashing: Mapping data via a one-way mathematical function that cannot be easily reversed
            // Encryption: Mapping data via a two-way mathematical fugsnction that can only be reversed with the correct inputs

            // This is encoding. Not encryption
            val secretPin = Base64.encodeToString(loginRepository.getUserPin().toByteArray(), Base64.DEFAULT)

            val mainGreeting = "\"Thanks for logging in using secret pin $secretPin\""
            val characterGreeting = character?.let { "${character.name} the ${character.species} says\n\n" }

            val greeting = characterGreeting?.let { it + mainGreeting } ?: mainGreeting
            _uiState.emit(MainViewState.Home(greeting))

        }
    }

}