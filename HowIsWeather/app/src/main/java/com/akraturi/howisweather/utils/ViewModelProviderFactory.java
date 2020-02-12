package com.akraturi.howisweather.utils;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.akraturi.howisweather.data.WeatherDataSource;
import com.akraturi.howisweather.viewmodels.WeatherViewmodel;


/**
 * Created by Amit K Raturi on 26/11/19.
 */
public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    private static ViewModelProviderFactory mFactory;
    private WeatherDataSource mDataSource;
    private Application mApplication;

    public static ViewModelProviderFactory getInstance(WeatherDataSource dataSource, Application application) {
        if (mFactory == null) {
            mFactory = new ViewModelProviderFactory(dataSource,application);
        }
        return mFactory;
    }

    private ViewModelProviderFactory(WeatherDataSource dataSource, Application application) {
        mDataSource = dataSource;
        mApplication = application;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(WeatherViewmodel.class)) {
            return (T) new WeatherViewmodel((WeatherDataSource) mDataSource,mApplication);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}