package com.trihard.konselink.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.trihard.konselink.models.panduan.PanduanListResponse;
import com.trihard.konselink.networking.PanduanRepository;

public class PanduanViewModel extends ViewModel {
    private MutableLiveData<PanduanListResponse> mutableLiveData;
    private PanduanRepository panduanRepository;

    public void init() {
        if (mutableLiveData != null) {
            return;
        }
        panduanRepository = PanduanRepository.getInstance();
        mutableLiveData = panduanRepository.getPanduans();
    }

    public LiveData<PanduanListResponse> getPanduanRepository() {
        return mutableLiveData;
    }
}
