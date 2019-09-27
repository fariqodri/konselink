package com.trihard.konselink.networking.repositories;

import androidx.lifecycle.MutableLiveData;
import com.trihard.konselink.models.panduan.PanduanListResponse;
import com.trihard.konselink.models.panduan.PanduanResponse;
import com.trihard.konselink.networking.RetrofitService;
import com.trihard.konselink.networking.api.PanduanApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PanduanRepository {
    private static PanduanRepository panduanRepository;

    public static PanduanRepository getInstance() {
        if (panduanRepository == null) {
            panduanRepository = new PanduanRepository();
        }
        return panduanRepository;
    }

    private PanduanApi panduanApi;

    private PanduanRepository() {
        panduanApi = RetrofitService.Companion.createService(PanduanApi.class);
    }

    public MutableLiveData<PanduanListResponse> findAll(String token) {
        final MutableLiveData<PanduanListResponse> panduanData = new MutableLiveData<>();
        panduanApi.getPanduanList(token).enqueue(new Callback<PanduanListResponse>() {
            @Override
            public void onResponse(Call<PanduanListResponse> call, Response<PanduanListResponse> response) {
                if (response.isSuccessful()) {
                    panduanData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<PanduanListResponse> call, Throwable t) {
                panduanData.setValue(null);
            }
        });
        return panduanData;
    }

    public MutableLiveData<PanduanResponse> findOne(String token, int id) {
        final MutableLiveData<PanduanResponse> panduan = new MutableLiveData<>();
        panduanApi.getPanduan(token, id).enqueue(new Callback<PanduanResponse>() {
            @Override
            public void onResponse(Call<PanduanResponse> call, Response<PanduanResponse> response) {
                if (response.isSuccessful()) {
                    panduan.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<PanduanResponse> call, Throwable t) {
                panduan.setValue(null);
            }
        });
        return panduan;
    }
}
