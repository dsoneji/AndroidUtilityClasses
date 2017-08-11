package com.android.practice.library.rest;

import android.content.Context;

import com.android.practice.library.AppConstant;
import com.android.practice.library.Applog;
import com.android.practice.library.ConnectivityUtils;
import com.android.practice.library.R;
import com.android.practice.library.Toaster;
import com.android.practice.library.interf.ApiProgressHelper;
import com.android.practice.library.model.Example;
import com.android.practice.library.model.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

/**
 * Retrofit api manager
 */
public class APIManagerR {
    private static final String TAG = APIManagerR.class.getSimpleName();
    private static APIManagerR instance = new APIManagerR();
    private RestClient restClient2;
    private RestClient restClient;

    private APIManagerR() {
        try {
            restClient = Singleton.getInstance().getRestAuthClient();
            restClient2 = Singleton.getInstance().getRestAuthClient2();
        } catch (Exception e) {
            Applog.e(TAG, e.getMessage(), e);
        }
    }

    public static APIManagerR getInstance() {
        if (instance == null) {
            instance = new APIManagerR();
        }
        return instance;
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
                Call<Example> responseCall = restClient.getAllPost(latitude, longitude,
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

    public void apiGetPostsData(Context context,
                                Callback<List<Posts>> responseCallback,
                                ApiProgressHelper apiProgressHelper) {
        try {
            if (ConnectivityUtils.checkInternetConnection(context)) {
                apiProgressHelper.startProgress();
                Call<List<Posts>> responseCall = restClient2.getAllPost();
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

    public void apiGetSpecificPostData(Context context,
                                       Callback<List<Posts>> responseCallback,
                                       ApiProgressHelper apiProgressHelper) {
        try {
            if (ConnectivityUtils.checkInternetConnection(context)) {
                apiProgressHelper.startProgress();
                Call<List<Posts>> responseCall = restClient2.getSpecificPost("1");
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

    public void apiGetPhotosData(Context context,
                                Callback<List<Posts>> responseCallback,
                                ApiProgressHelper apiProgressHelper) {
        try {
            if (ConnectivityUtils.checkInternetConnection(context)) {
                apiProgressHelper.startProgress();
                Call<List<Posts>> responseCall = restClient2.getAllPhotos();
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
}
