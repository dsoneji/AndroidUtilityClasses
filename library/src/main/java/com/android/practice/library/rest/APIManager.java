package com.android.practice.library.rest;

import android.content.Context;

import com.android.practice.library.AppConstant;
import com.android.practice.library.Applog;
import com.android.practice.library.ConnectivityUtils;
import com.android.practice.library.Toaster;
import com.android.practice.library.interf.ApiProgressHelper;
import com.android.practice.library.mode.Example;
import com.android.practice.library.mode.ServerResponse;

import retrofit2.Call;
import retrofit2.Callback;

public class APIManager {
    private static final String TAG = APIManager.class.getSimpleName();
    private static APIManager instance = new APIManager();
    private RestClient restClient;

    private APIManager() {
        try {
            restClient = Singleton.getInstance().getRestAuthClient();
        } catch (Exception e) {
            Applog.e(TAG, e.getMessage(), e);
        }
    }

    public static APIManager getInstance() {
        if (instance == null) {
            instance = new APIManager();
        }
        return instance;
    }

    public void stopRunningAPI() {
        Singleton.getInstance().stopRunningAPI();
    }

    public void apiWeatherByLatLong(Context context,
                                    String latitude, String longitude,
                                    Callback<ServerResponse<Example>> responseCallback,
                                    ApiProgressHelper apiProgressHelper) {
        try {
            if (ConnectivityUtils.checkInternetConnection(context)) {
                apiProgressHelper.startProgress();
                Call<ServerResponse<Example>> responseCall = Singleton.getInstance()
                        .getRestAuthClient().weatherByLatLong(latitude, longitude,
                                AppConstant.API_KEY_OPEN_WEATHER_MAP);
                responseCall.enqueue(responseCallback);
            } else {
                apiProgressHelper.onException();
                Toaster.longToast("No internet connection found!");
            }

        } catch (Exception e) {
            Applog.e(TAG, e.getMessage(), e);
        }
    }
}
