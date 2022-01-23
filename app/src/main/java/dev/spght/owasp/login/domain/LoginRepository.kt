package dev.spght.owasp.login.domain

interface LoginRepository {
    fun saveUserPin(pin: List<Int>)
    fun getUserPin(): String
}