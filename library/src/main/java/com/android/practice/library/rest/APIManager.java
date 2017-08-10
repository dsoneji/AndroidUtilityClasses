package com.android.practice.library.rest;

import android.content.Context;

import com.android.practice.library.AppConstant;
import com.android.practice.library.Applog;
import com.android.practice.library.ConnectivityUtils;
import com.android.practice.library.R;
import com.android.practice.library.Toaster;
import com.android.practice.library.interf.ApiProgressHelper;
import com.android.practice.library.mode.Example;
import com.android.practice.library.mode.ServerResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import retrofit2.Call;
import retrofit2.Callback;

public class APIManager {
    private static final String TAG = APIManager.class.getSimpleName();
    private static APIManager instance = new APIManager();
    private static APIManager instance2;
    private RequestQueue queue;
    private RestClient restClient;
    public static final String VOLLEY_TAG = "my.tag";

    private APIManager() {
        try {
            restClient = Singleton.getInstance().getRestAuthClient();

        } catch (Exception e) {
            Applog.e(TAG, e.getMessage(), e);
        }
    }

    private APIManager(Context context) {
        try {
            queue = Volley.newRequestQueue(context);
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

    public static APIManager getInstance2(Context context) {
        if (instance2 == null) {
            VolleyLog.DEBUG = true;
            instance2 = new APIManager(context);
        }
        return instance2;
    }

    public void stopRunningAPI() {
        Singleton.getInstance().stopRunningAPI();
    }

    public void apiWeatherByLatLong(Context context,
                                    String latitude, String longitude,
                                    Callback<Example> responseCallback,
                                    ApiProgressHelper apiProgressHelper) {
        try {
            if (ConnectivityUtils.checkInternetConnection(context)) {
                apiProgressHelper.startProgress();
                Call<Example> responseCall = restClient.weatherByLatLong(latitude, longitude,
                        AppConstant.API_KEY_OPEN_WEATHER_MAP);
                responseCall.enqueue(responseCallback);
            } else {
                apiProgressHelper.onException();
                Toaster.longToast("No internet connection found!");
            }

        } catch (Exception e) {
            apiProgressHelper.onException();
            Toaster.longToast(context.getResources().getString(R.string.oops_something_went_wrong));
            Applog.e(TAG, e.getMessage(), e);
        }
    }

    public void apiWeatherByLatLongV(Context context, Response.Listener<String> responseCallBack,
                                     Response.ErrorListener errorCallBack,
                                     ApiProgressHelper apiProgressHelper,
                                     String latitude, String longitude) {
        try {
            if (ConnectivityUtils.checkInternetConnection(context)) {
                apiProgressHelper.startProgress();
                String url = AppConstant.BASE_URL_OPEN_WEATHER_MAP + "weather?lat=" + latitude +
                        "&lon=" + longitude + "&appid=" + AppConstant.API_KEY_OPEN_WEATHER_MAP;
                StringRequest stringRequest = new StringRequest(Request.Method.GET,
                        url, responseCallBack, errorCallBack){

                };
                stringRequest.setTag(TAG);
                queue.add(stringRequest);
            } else {
                apiProgressHelper.onException();
                Toaster.longToast("No internet connection found!");
            }
        } catch (Exception e) {
            apiProgressHelper.onException();
            Toaster.longToast(context.getResources().getString(R.string.oops_something_went_wrong));
            Applog.e(TAG, e.getMessage(), e);
        }
    }

    public void stopAllVolleyRequest() {
        if (queue != null) {
            queue.cancelAll(VOLLEY_TAG);
        }
    }
}
