package com.trihard.konselink.networking;

import androidx.lifecycle.MutableLiveData;
import com.trihard.konselink.models.panduan.PanduanListResponse;
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

    public PanduanRepository() {
        panduanApi = RetrofitService.Companion.createService(PanduanApi.class);
    }

    public MutableLiveData<PanduanListResponse> getPanduans() {
        final MutableLiveData<PanduanListResponse> panduanData = new MutableLiveData<>();
        panduanApi.getPanduanList().enqueue(new Callback<PanduanListResponse>() {
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
}
