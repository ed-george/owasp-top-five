package dev.spght.owasp.login

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dev.spght.owasp.databinding.ActivityLoginBinding
import dev.spght.owasp.home.MainActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val viewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        binding.button1.setOnClickListener { viewModel.didSelect(1) }
        binding.button2.setOnClickListener { viewModel.didSelect(2) }
        binding.button3.setOnClickListener { viewModel.didSelect(3) }
        binding.button4.setOnClickListener { viewModel.didSelect(4) }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                collectUiState()
            }
        }
    }

    private suspend fun collectUiState() {
        viewModel.event.collect { uiState ->
            when (uiState) {
                LoginViewState.PinCorrect -> goToNextScreen()
                is LoginViewState.PinEntered -> showPinEntered(uiState.pinCount)
                else -> {
                    // NO-OP
                }
            }
        }
    }

    private fun showPinEntered(pinCount: Int) {
        binding.mainPin.text = "*".repeat(pinCount)
    }

    // The user got the PIN right, take them to our super secure home screen!
    // This is the only way they can get there... right?
    private fun goToNextScreen() {
        startActivity(
            Intent(this, MainActivity::class.java)
        )
    }

}