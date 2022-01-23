package dev.spght.owasp.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import dev.spght.owasp.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                collectUiState()
            }
        }

        viewModel.start()

    }

    private suspend fun collectUiState() {
        viewModel.uiState.collect { uiState ->
            when (uiState) {
                is MainViewState.Home -> {
                    // No longer system-wide view
                    // ... still not a great idea to display this though! :)
                    binding.mainTextView.append("\n\n" + uiState.userGreeting)
                }
                else -> {
                    // NO-OP
                }
            }
        }
    }

}