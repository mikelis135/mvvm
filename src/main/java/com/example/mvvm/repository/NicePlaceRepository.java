package com.example.mvvm.repository;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvm.model.NicePlace;

import java.util.ArrayList;
import java.util.List;

public class NicePlaceRepository {

    private static NicePlaceRepository instance;
    private ArrayList<NicePlace> dataSet = new ArrayList<>();

    public static NicePlaceRepository getInstance(){

        //singleton of Repository to prevent multiple instance of Repo
        if (instance == null){
            instance = new NicePlaceRepository();
        }
        return instance;
    }

    public MutableLiveData<List<NicePlace>> getNicePlaces(){
        MutableLiveData<List<NicePlace>> nicePlaceMutableLiveData = new MutableLiveData<>();

        if (dataSet.size() != 0) {

            //will come here henceforth for consecutive data
            nicePlaceMutableLiveData.setValue(dataSet);
            return nicePlaceMutableLiveData;
        }

        //will come here for first gettimng default values from static data
        setNicePlaces();
        nicePlaceMutableLiveData.setValue(dataSet);
        return nicePlaceMutableLiveData;
    }

    private void setNicePlaces() {
        //static data
        dataSet.add(new NicePlace("Lagos", ""));
        dataSet.add(new NicePlace("London", ""));
        dataSet.add(new NicePlace("London", ""));
    }

    public MutableLiveData<List<NicePlace>> setNewNicePlaces(String name, String surname){

        dataSet.add(new NicePlace(name, surname));

        //livata object for setting new data
        MutableLiveData<List<NicePlace>> nicePlaceMutableLiveData = new MutableLiveData<>();
        nicePlaceMutableLiveData.setValue(dataSet);
        return nicePlaceMutableLiveData;
    }
}
