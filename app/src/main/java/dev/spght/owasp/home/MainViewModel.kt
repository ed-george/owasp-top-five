package dev.spght.owasp.home

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
            val mainGreeting = "\"Thanks for logging in using ${loginRepository.getUserPin()}\""
            val characterGreeting = character?.let { "${character.name} the ${character.species} says\n\n" }

            val greeting = characterGreeting?.let { it + mainGreeting } ?: mainGreeting
            _uiState.emit(MainViewState.Home(greeting))

        }
    }

}