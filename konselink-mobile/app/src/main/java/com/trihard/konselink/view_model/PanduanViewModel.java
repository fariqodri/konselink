package com.trihard.konselink.view_model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.trihard.konselink.models.panduan.PanduanListResponse;
import com.trihard.konselink.models.panduan.PanduanResponse;
import com.trihard.konselink.networking.repositories.PanduanRepository;

public class PanduanViewModel extends ViewModel {
    private MutableLiveData<PanduanResponse> oneData;
    private MutableLiveData<PanduanListResponse> listData;
    private PanduanRepository panduanRepository;

    public void loadOne(String token, int id) {
        if (oneData != null) {
            return;
        }
        panduanRepository = PanduanRepository.getInstance();
        oneData = panduanRepository.findOne(token, id);
    }

    public void loadAll(String token) {
        if (listData != null) {
            return;
        }
        panduanRepository = PanduanRepository.getInstance();
        listData = panduanRepository.findAll(token);
    }

    public LiveData<PanduanResponse> findOne() {
        return oneData;
    }

    public LiveData<PanduanListResponse> findAll() {
        return listData;
    }
}
