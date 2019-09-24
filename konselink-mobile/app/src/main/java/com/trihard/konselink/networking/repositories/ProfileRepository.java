package com.trihard.konselink.networking.repositories;

import androidx.lifecycle.MutableLiveData;
import com.trihard.konselink.models.profile.AccountResponse;
import com.trihard.konselink.models.profile.LoginBody;
import com.trihard.konselink.models.profile.RegisterBody;
import com.trihard.konselink.networking.RetrofitService;
import com.trihard.konselink.networking.api.ProfileApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileRepository {
    private static ProfileRepository profileRepository;

    public static ProfileRepository getInstance() {
        if (profileRepository == null) {
            profileRepository = new ProfileRepository();
        }
        return profileRepository;
    }

    private ProfileApi profileApi;

    private ProfileRepository() {
        profileApi = RetrofitService.Companion.createService(ProfileApi.class);
    }

    public MutableLiveData<AccountResponse> findOne(String authToken) {
        final MutableLiveData<AccountResponse> responseData = new MutableLiveData<>();
        profileApi.getUserData(authToken).enqueue(new Callback<AccountResponse>() {
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                if (response.isSuccessful()) {
                    responseData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AccountResponse> call, Throwable t) {
                responseData.setValue(null);
            }
        });
        return responseData;
    }

    public MutableLiveData<AccountResponse> login(LoginBody loginBody) {
        final MutableLiveData<AccountResponse> loginResponseData = new MutableLiveData<>();
        profileApi.login(loginBody).enqueue(new Callback<AccountResponse>() {
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                if (response.isSuccessful()) {
                    loginResponseData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AccountResponse> call, Throwable t) {
                loginResponseData.setValue(null);
            }
        });
        return loginResponseData;
    }

    public MutableLiveData<AccountResponse> register(RegisterBody registerBody) {
        final MutableLiveData<AccountResponse> registerResponseData = new MutableLiveData<>();
        profileApi.register(registerBody).enqueue(new Callback<AccountResponse>() {
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                if (response.isSuccessful()) {
                    registerResponseData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AccountResponse> call, Throwable t) {
                registerResponseData.setValue(null);
            }
        });
        return registerResponseData;
    }
}
