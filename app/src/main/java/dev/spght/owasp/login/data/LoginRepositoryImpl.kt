package dev.spght.owasp.login.data

import android.content.SharedPreferences
import dev.spght.owasp.login.domain.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : LoginRepository {

    override fun saveUserPin(pin: List<Int>) {
        // M2: Insecure Data Storage
        // Shared preferences is NOT secure data storage
        // Data is stored in plaintext within an XML file that is easily accessible on the device
        //
        // Consider using a secure storage solution such as AndroidX Security or Room + sqlcipher
        sharedPreferences.edit().putString(PREF_KEY, pin.joinToString(separator = "")).apply()
    }

    override fun getUserPin(): String = sharedPreferences.getString(PREF_KEY, "")!!

    companion object {
        private const val PREF_KEY = "secret_user_pin"
    }

}