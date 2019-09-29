package com.trihard.konselink.networking.repositories;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.trihard.konselink.R;
import com.trihard.konselink.models.profile.AccountBody;
import com.trihard.konselink.models.profile.AccountResponse;
import com.trihard.konselink.models.profile.LoginBody;
import com.trihard.konselink.models.profile.RegisterBody;
import com.trihard.konselink.networking.RetrofitService;
import com.trihard.konselink.networking.api.ProfileApi;

import org.jetbrains.annotations.NotNull;

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

    public void findOne(String authToken, final MutableLiveData<AccountBody> responseData) {
        profileApi.getUserData(authToken).enqueue(new Callback<AccountBody>() {
            @Override
            public void onResponse(Call<AccountBody> call, Response<AccountBody> response) {
                if (response.isSuccessful()) {
                    responseData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<AccountBody> call, Throwable t) {}
        });
    }

    public void login(LoginBody loginBody, final MutableLiveData<AccountResponse> loginResponseData) {
        profileApi.login(loginBody).enqueue(new Callback<AccountBody>() {
            @Override
            public void onResponse(Call<AccountBody> call, Response<AccountBody> response) {
                if (response.isSuccessful()) {
                    loginResponseData.setValue(new AccountResponse(response.body()));
                } else {
                    loginResponseData.setValue(new AccountResponse(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<AccountBody> call, Throwable t) {

            }
        });
    }

    public void register(RegisterBody registerBody, final MutableLiveData<AccountResponse> registerResponseData) {
        profileApi.register(registerBody).enqueue(new Callback<AccountBody>() {
            @Override
            public void onResponse(Call<AccountBody> call, Response<AccountBody> response) {
                if (response.isSuccessful()) {
                    registerResponseData.setValue(new AccountResponse(response.body()));
                } else {
                    registerResponseData.setValue(new AccountResponse(response.errorBody()));
                }
            }

            @Override
            public void onFailure(Call<AccountBody> call, Throwable t) {}
        });
    }

    public void setUserId(@NotNull SharedPreferences.Editor editor, @NotNull Context context, int userId) {
        editor.putInt(context.getString(R.string.user_id_key), userId);
    }

    public void setToken(@NotNull SharedPreferences.Editor editor, @NotNull Context context, String token) {
        editor.putString(context.getString(R.string.token_key), token);
    }

    public void setRole(@NotNull SharedPreferences.Editor editor, @NotNull Context context, String role) {
        editor.putString(context.getString(R.string.role_key), role);
    }

    public void clearUserData(@NotNull SharedPreferences.Editor editor) {
        editor.clear();
    }

    public String getToken(@NotNull SharedPreferences preferences, @NotNull Context context) {
        return preferences.getString(context.getString(R.string.token_key), null);
    }


}
