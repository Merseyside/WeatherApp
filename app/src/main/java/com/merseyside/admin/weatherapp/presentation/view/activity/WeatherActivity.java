package com.merseyside.admin.weatherapp.presentation.view.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import com.merseyside.admin.weatherapp.R;
import com.merseyside.admin.weatherapp.presentation.WeatherApplication;
import com.merseyside.admin.weatherapp.presentation.view.fragment.WeatherFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeatherActivity extends BaseActivity implements MainViewListener{

    @BindView(R.id.refresh_fab) FloatingActionButton refreshBtn;
    private WeatherFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if (savedInstanceState == null) {
            addFragment(R.id.weather_fragment, fragment = new WeatherFragment());
        }
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof WeatherFragment)
            this.fragment = (WeatherFragment) fragment;
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @OnClick(R.id.refresh_fab)
    public void refreshWeather() {
        if (fragment!= null) fragment.getWeather();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.exit) {
            navigator.exit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onComplete() {
        showMessage(refreshBtn, getString(R.string.refreshed));
    }

    @Override
    public void onError(String msg) {
        showMessage(refreshBtn, msg);
    }

}
