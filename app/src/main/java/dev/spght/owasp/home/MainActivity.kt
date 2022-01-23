package dev.spght.owasp.home

import android.os.Bundle
import android.widget.Toast
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
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                collectUiState()
            }
        }

        viewModel.start()

    }

    private suspend fun collectUiState() {
        viewModel.uiState.collect { uiState ->
            when (uiState) {
                is MainViewState.Home -> {
                    // M2: Insecure Data Storage / Leakage
                    // The use of a Toast here exposes the data as a system-wide view
                    // In theory, these views can be captured by other malicious apps
                    // Do NOT put any sensitive data in system-wide views
                    Toast.makeText(this, uiState.userGreeting, Toast.LENGTH_LONG).show()
                }
                else -> {
                    // NO-OP
                }
            }
        }
    }

}