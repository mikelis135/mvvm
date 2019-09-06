package com.example.mvvm.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvm.model.NicePlace;
import com.example.mvvm.repository.NicePlaceRepository;

import java.util.List;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<List<NicePlace>> nicePlaceMutableLiveData;
    private NicePlaceRepository nicePlaceRepository;

    public void init(){
        if (nicePlaceMutableLiveData != null){
            return;
        }
        nicePlaceRepository = NicePlaceRepository.getInstance();
        nicePlaceMutableLiveData = nicePlaceRepository.getNicePlaces();
    }

    public LiveData<List<NicePlace>> getNicePlaceMutableLiveData() {
        return nicePlaceMutableLiveData;
    }
    public LiveData<List<NicePlace>> setNicePlaceMutableLiveData(NicePlace nicePlace) {
        return nicePlaceRepository.setNewNicePlaces(nicePlace.getName(), nicePlace.getSurname());
    }
}
