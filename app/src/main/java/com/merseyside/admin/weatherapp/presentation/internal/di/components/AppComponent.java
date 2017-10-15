package com.merseyside.admin.weatherapp.presentation.internal.di.components;

/**
 * Created by ivan_ on 09.10.2017.
 */

import com.merseyside.admin.weatherapp.data.WeatherInteractorImpl;
import com.merseyside.admin.weatherapp.presentation.internal.di.modules.AppModule;
import com.merseyside.admin.weatherapp.presentation.view.activity.BaseActivity;
import com.merseyside.admin.weatherapp.presentation.view.adapters.WeatherAdapter;
import com.merseyside.admin.weatherapp.presentation.view.fragment.WeatherFragment;

import javax.inject.Singleton;

import dagger.Component;


@Component(modules = {AppModule.class})
@Singleton
public interface AppComponent {
    void inject(BaseActivity baseActivity);
    void inject(WeatherInteractorImpl weatherInteractor);
    void inject(WeatherFragment weatherFragment);
    void inject(WeatherAdapter weatherAdapter);
}
