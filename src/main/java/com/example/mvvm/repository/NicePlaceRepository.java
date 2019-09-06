package com.example.mvvm.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.model.NicePlace;

import java.util.ArrayList;
import java.util.List;

public class NicePlaceRepository {

    private static NicePlaceRepository instance;
    private ArrayList<NicePlace> dataSet = new ArrayList<>();

    public static NicePlaceRepository getInstance(){
        if (instance == null){
            instance = new NicePlaceRepository();
        }
        return instance;
    }

    public MutableLiveData<List<NicePlace>> getNicePlaces(){
        MutableLiveData<List<NicePlace>> nicePlaceMutableLiveData = new MutableLiveData<>();
        if (dataSet.size() != 0) {
            nicePlaceMutableLiveData.setValue(dataSet);
            return nicePlaceMutableLiveData;
        }
        setNicePlaces();
        nicePlaceMutableLiveData.setValue(dataSet);
        return nicePlaceMutableLiveData;
    }

    private void setNicePlaces() {
        dataSet.add(new NicePlace("Lagos", ""));
        dataSet.add(new NicePlace("London", ""));
        dataSet.add(new NicePlace("London", ""));
    }

    public MutableLiveData<List<NicePlace>> setNewNicePlaces(String name, String surname){
        dataSet.add(new NicePlace(name, surname));
        MutableLiveData<List<NicePlace>> nicePlaceMutableLiveData = new MutableLiveData<>();
        nicePlaceMutableLiveData.setValue(dataSet);
        return nicePlaceMutableLiveData;
    }
}
