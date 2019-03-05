package com.example.myapplication.model.repository.main;

import com.example.myapplication.PostModel;
import com.example.myapplication.model.data.api.UmoriliApi;
import com.example.myapplication.untils.DataCallback;
import com.example.myapplication.untils.FailureCallback;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainRepository {
    UmoriliApi api;

    public MainRepository(UmoriliApi api) {
        this.api = api;
    }

    public void getData(String name, int count, final DataCallback callback, final FailureCallback failureCallback) {
        api.getData(name, count).enqueue(new Callback<List<PostModel>>() {
            @Override
            public void onResponse(Call<List<PostModel>> call, Response<List<PostModel>> response) {
                callback.setList(response.body());
            }

            @Override
            public void onFailure(Call<List<PostModel>> call, Throwable t) {
                failureCallback.setError(t);
            }
        });
    }
}
