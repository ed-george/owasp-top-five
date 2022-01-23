package dev.spght.owasp.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.spght.owasp.home.MainViewState.Loading
import dev.spght.owasp.login.domain.LoginRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow<MainViewState>(Loading)
    val uiState: StateFlow<MainViewState> = _uiState.asStateFlow()

    fun start() {
        viewModelScope.launch {
            delay(1000)
            val greeting = "Thanks for logging in using ${repository.getUserPin()}"
            _uiState.emit(MainViewState.Home(greeting))
        }
    }

}