package com.trihard.konselink.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trihard.konselink.models.profile.AccountBody
import com.trihard.konselink.models.profile.AccountResponse
import com.trihard.konselink.models.profile.LoginBody
import com.trihard.konselink.models.profile.RegisterBody
import com.trihard.konselink.networking.repositories.ProfileRepository

class UserViewModel : ViewModel() {

    private val profileRepository = ProfileRepository.getInstance()
    val accountResult: MutableLiveData<AccountBody> by lazy {
        MutableLiveData<AccountBody>()
    }
    val loginResult: MutableLiveData<AccountResponse> by lazy {
        MutableLiveData<AccountResponse>()
    }
    val registerResult: MutableLiveData<AccountResponse> by lazy {
        MutableLiveData<AccountResponse>()
    }

    fun login(loginBody: LoginBody) {
        profileRepository.login(loginBody, loginResult)
    }

    fun register(registerBody: RegisterBody) {
        profileRepository.register(registerBody, registerResult)
    }

    fun findOne(authToken: String) {
        profileRepository.findOne(authToken, accountResult)
    }
}