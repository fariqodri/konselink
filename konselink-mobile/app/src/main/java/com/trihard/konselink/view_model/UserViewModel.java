package com.trihard.konselink.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.trihard.konselink.models.profile.AccountResponse;
import com.trihard.konselink.models.profile.LoginBody;
import com.trihard.konselink.models.profile.RegisterBody;
import com.trihard.konselink.networking.repositories.ProfileRepository;

public class UserViewModel extends ViewModel {
    private MutableLiveData<AccountResponse> getAccountResult;
    private MutableLiveData<AccountResponse> loginResult;
    private MutableLiveData<AccountResponse> registerResult;
    private ProfileRepository profileRepository = ProfileRepository.getInstance();

    public void loadOne(String authToken) {
        if (getAccountResult != null) {
            return;
        }
        getAccountResult = profileRepository.findOne(authToken);
    }

    public void loginInit(LoginBody loginBody) {
        if (loginResult != null) {
            return;
        }
        loginResult = profileRepository.login(loginBody);
    }

    public void registerInit(RegisterBody registerBody) {
        if (registerResult != null) {
            return;
        }
        registerResult = profileRepository.register(registerBody);
    }

    public LiveData<AccountResponse> findOne() {
        return getAccountResult;
    }

    public LiveData<AccountResponse> login() {
        return loginResult;
    }

    public LiveData<AccountResponse> register() {
        return registerResult;
    }
}
