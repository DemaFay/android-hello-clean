package com.example.myapplication.ui.main;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    ViewGroup flFrameContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        flFrameContainer = findViewById(R.id.flFrameContainer);
        if (savedInstanceState == null) {
            FragmentManager fm = getSupportFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(flFrameContainer.getId(), new MainFragment());
            ft.commit();
        }
    }
}
