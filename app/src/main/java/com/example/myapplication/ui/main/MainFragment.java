package com.example.myapplication.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.myapplication.App;
import com.example.myapplication.PostModel;
import com.example.myapplication.R;
import com.example.myapplication.model.interactor.MainInteractor;
import com.example.myapplication.model.repository.main.MainRepository;
import com.example.myapplication.presentation.main.MainPresenter;
import com.example.myapplication.presentation.main.MainView;
import com.example.myapplication.ui.main.adapter.PostsAdapter;

import java.util.List;

public class MainFragment extends Fragment implements MainView {

    private static final int LAYOUT = R.layout.main_fragment;

    View view;
    RecyclerView rvPosts;
    LinearLayoutManager layoutManager;
    PostsAdapter adapter;

    MainPresenter presenter;

    private String RECYCLER_POSITION = "recycler_position";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        if (presenter == null) {
            presenter = new MainPresenter(this, new MainInteractor(new MainRepository(App.getApi())));
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(LAYOUT, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        rvPosts = view.findViewById(R.id.rvPosts);
        layoutManager = new LinearLayoutManager(getContext());
        rvPosts.setLayoutManager(layoutManager);

        adapter = new PostsAdapter();
        rvPosts.setAdapter(adapter);

        if (savedInstanceState != null) {
            layoutManager.scrollToPosition(savedInstanceState.getInt(RECYCLER_POSITION));
        }

        presenter.setData();
    }

    @Override
    public void setList(final List<PostModel> models) {
        rvPosts.post(new Runnable() {
            @Override
            public void run() {
                adapter.setPosts(models);
            }
        });
    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(getContext(), "Error: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(RECYCLER_POSITION, layoutManager.findFirstVisibleItemPosition());
    }
}
