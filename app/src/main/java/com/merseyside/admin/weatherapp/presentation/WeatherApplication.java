package com.merseyside.admin.weatherapp.presentation;

import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import com.merseyside.admin.weatherapp.R;
import com.merseyside.admin.weatherapp.data.net.DarkSkyApi;
import com.merseyside.admin.weatherapp.presentation.internal.di.components.AppComponent;
import com.merseyside.admin.weatherapp.presentation.internal.di.components.DaggerAppComponent;
import com.merseyside.admin.weatherapp.presentation.internal.di.modules.AppModule;

/**
 * Created by ivan_ on 09.10.2017.
 */

public class WeatherApplication extends Application {

    private static Point dimensions;
    private static AppComponent component;
    public static AppComponent getApplicationComponent(){
        return component;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        getScreenDimensions(this);
        component = buildComponent();
        DarkSkyApi.create(getResources().getString(R.string.dark_sky_api));
    }

    private AppComponent buildComponent(){
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

    }

    private void getScreenDimensions(Context context){
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);
        dimensions = size;
    }

    public static Point getDimensions() {
        return dimensions;
    }

    public static void close() {
        System.exit(0);
    }
}
