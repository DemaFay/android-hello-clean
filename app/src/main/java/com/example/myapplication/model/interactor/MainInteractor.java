package com.example.myapplication.model.interactor;

import com.example.myapplication.model.repository.main.MainRepository;
import com.example.myapplication.untils.DataCallback;
import com.example.myapplication.untils.FailureCallback;

public class MainInteractor {

    private MainRepository repository;
    private static final String BASH = "bash";

    public MainInteractor(MainRepository repository) {
        this.repository = repository;
    }

    public void getBashData(int count, final DataCallback callback, final FailureCallback failureCallback) {
        repository.getData(BASH, count, callback, failureCallback);
    }
}
