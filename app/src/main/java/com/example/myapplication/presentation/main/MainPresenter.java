package com.example.myapplication.presentation.main;

import com.example.myapplication.PostModel;
import com.example.myapplication.model.interactor.MainInteractor;
import com.example.myapplication.untils.DataCallback;
import com.example.myapplication.untils.FailureCallback;

import java.util.List;

public class MainPresenter {

    private MainView view;
    private MainInteractor interactor;

    public MainPresenter(MainView view, MainInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void setData() {
        interactor.getBashData(50, new DataCallback() {
            @Override
            public void setList(List<PostModel> models) {
                view.setList(models);
            }
        }, new FailureCallback() {
            @Override
            public void setError(Throwable ex) {
                view.showErrorMessage(ex.getMessage());
            }
        });
    }
}
