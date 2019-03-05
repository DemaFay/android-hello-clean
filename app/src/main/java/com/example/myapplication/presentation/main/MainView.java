package com.example.myapplication.presentation.main;

import com.example.myapplication.PostModel;

import java.util.List;

public interface MainView {
    void setList(List<PostModel> models);
    void showErrorMessage(String message);
}
