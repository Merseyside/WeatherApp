package com.merseyside.admin.weatherapp.presentation.view.activity;

import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.merseyside.admin.weatherapp.R;
import com.merseyside.admin.weatherapp.presentation.WeatherApplication;
import com.merseyside.admin.weatherapp.presentation.internal.di.components.AppComponent;
import com.merseyside.admin.weatherapp.presentation.navigation.Navigator;

import javax.inject.Inject;

/**
 * Created by ivan_ on 09.10.2017.
 */

public abstract class BaseActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
    @Inject
    Navigator navigator;

    @Inject
    Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    protected AppComponent getApplicationComponent() {
        return WeatherApplication.getApplicationComponent();
    }

    protected void addFragment(int containerViewId, Fragment fragment) {
        final FragmentTransaction fragmentTransaction = this.getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    protected void showMessage(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).show();
    }

}
