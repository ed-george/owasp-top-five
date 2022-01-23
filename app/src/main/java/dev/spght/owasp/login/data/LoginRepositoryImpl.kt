package dev.spght.owasp.login.data

import android.content.SharedPreferences
import dev.spght.owasp.login.domain.LoginRepository
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : LoginRepository {

    override fun saveUserPin(pin: List<Int>) {
        // This pref is now stored in AndroidX Security Lib's EncryptedSharedPreferences
        // that utilises the Android keystore system and cyptography to make sure data is stored
        // securely and is no longer stored unencrypted on the device
        // See: https://developer.android.com/topic/security/data#edit-shared-preferences
        sharedPreferences.edit().putString(PREF_KEY, pin.joinToString(separator = "")).apply()
    }

    override fun getUserPin(): String = sharedPreferences.getString(PREF_KEY, "")!!

    companion object {
        private const val PREF_KEY = "secret_user_pin"
    }

}