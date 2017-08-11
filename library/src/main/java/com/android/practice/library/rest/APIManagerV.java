package com.android.practice.library.rest;

import android.content.Context;

import com.android.practice.library.AppConstant;
import com.android.practice.library.Applog;
import com.android.practice.library.ConnectivityUtils;
import com.android.practice.library.R;
import com.android.practice.library.Toaster;
import com.android.practice.library.interf.VolleyResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/**
 * Volley api manager
 */
public class APIManagerV {
    private static final String TAG = APIManagerV.class.getSimpleName();
    private static APIManagerV instance;
    private RequestQueue queue;
    public static final String VOLLEY_TAG = "my.tag";

    private APIManagerV(Context context) {
        try {
            queue = Volley.newRequestQueue(context);
        } catch (Exception e) {
            Applog.e(TAG, e.getMessage(), e);
        }
    }

    public static APIManagerV getInstance(Context context) {
        if (instance == null) {
            VolleyLog.DEBUG = true;
            instance = new APIManagerV(context);
        }
        return instance;
    }

    public void apiWeatherByLatLongV(Context context,
                                     final VolleyResponse responseCallBack,
                                     String latitude, String longitude) {
        try {
            if (ConnectivityUtils.checkInternetConnection(context)) {
                responseCallBack.startProgress();
                String url = AppConstant.BASE_URL_OPEN_WEATHER_MAP + "weather?lat=" + latitude +
                        "&lon=" + longitude + "&appid=" + AppConstant.API_KEY_OPEN_WEATHER_MAP;
                StringRequest stringRequest = new StringRequest(Request.Method.GET,
                        url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        responseCallBack.onResponse(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        responseCallBack.onException(error);
                    }
                });
                stringRequest.setTag(TAG);
                queue.add(stringRequest);
            } else {
                responseCallBack.onException();
                Toaster.longToast("No internet connection found!");
            }
        } catch (Exception e) {
            responseCallBack.onException();
            Toaster.longToast(context.getResources().getString(R.string.oops_something_went_wrong));
            Applog.e(TAG, e.getMessage(), e);
        }
    }

    public void apiGetPostsData(Context context,
                                final VolleyResponse responseCallBack) {
        try {
            if (ConnectivityUtils.checkInternetConnection(context)) {
                responseCallBack.startProgress();
                String url = AppConstant.BASE_URL_JSON_PLACEHOLDER + "posts";

                StringRequest stringRequest = new StringRequest(Request.Method.GET,
                        url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        responseCallBack.onResponse(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        responseCallBack.onException(error);
                    }
                });
                stringRequest.setTag(TAG);
                queue.add(stringRequest);
            } else {
                responseCallBack.onException();
                Toaster.longToast("No internet connection found!");
            }
        } catch (Exception e) {
            responseCallBack.onException();
            Toaster.longToast(context.getResources().getString(R.string.oops_something_went_wrong));
            Applog.e(TAG, e.getMessage(), e);
        }
    }

    public void apiGetSpecificPostData(Context context,
                                       final VolleyResponse responseCallBack) {
        try {
            if (ConnectivityUtils.checkInternetConnection(context)) {
                responseCallBack.startProgress();
                String url = AppConstant.BASE_URL_JSON_PLACEHOLDER + "posts/1";

                StringRequest stringRequest = new StringRequest(Request.Method.GET,
                        url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        responseCallBack.onResponse(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        responseCallBack.onException(error);
                    }
                });
                stringRequest.setTag(TAG);
                queue.add(stringRequest);
            } else {
                responseCallBack.onException();
                Toaster.longToast("No internet connection found!");
            }
        } catch (Exception e) {
            responseCallBack.onException();
            Toaster.longToast(context.getResources().getString(R.string.oops_something_went_wrong));
            Applog.e(TAG, e.getMessage(), e);
        }
    }

    public void apiGetPhotosData(Context context,
                                 final VolleyResponse responseCallBack) {
        try {
            if (ConnectivityUtils.checkInternetConnection(context)) {
                responseCallBack.startProgress();
                String url = AppConstant.BASE_URL_JSON_PLACEHOLDER + "photos";

                StringRequest stringRequest = new StringRequest(Request.Method.GET,
                        url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        responseCallBack.onResponse(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        responseCallBack.onException(error);
                    }
                });
                stringRequest.setTag(TAG);
                queue.add(stringRequest);
            } else {
                responseCallBack.onException();
                Toaster.longToast("No internet connection found!");
            }
        } catch (Exception e) {
            responseCallBack.onException();
            Toaster.longToast(context.getResources().getString(R.string.oops_something_went_wrong));
            Applog.e(TAG, e.getMessage(), e);
        }
    }

    public void apiGetRandomDog(Context context,
                                final VolleyResponse responseCallBack) {
        try {
            if (ConnectivityUtils.checkInternetConnection(context)) {
                responseCallBack.startProgress();
                String url = AppConstant.BASE_URL_RANDOM_DOG;

                StringRequest stringRequest = new StringRequest(Request.Method.GET,
                        url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        responseCallBack.onResponse(response);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        responseCallBack.onException(error);
                    }
                });
                stringRequest.setTag(TAG);
                queue.add(stringRequest);
            } else {
                responseCallBack.onException();
                Toaster.longToast("No internet connection found!");
            }
        } catch (Exception e) {
            responseCallBack.onException();
            Toaster.longToast(context.getResources().getString(R.string.oops_something_went_wrong));
            Applog.e(TAG, e.getMessage(), e);
        }

    }

    public void stopAllVolleyRequest() {
        if (queue != null) {
            queue.cancelAll(VOLLEY_TAG);
        }
    }

    /*
    public void dummy(Context context,
                                final VolleyResponse responseCallBack) {
        try {
            if (ConnectivityUtils.checkInternetConnection(context)) {
                responseCallBack.startProgress();

            } else {
                responseCallBack.onException();
                Toaster.longToast("No internet connection found!");
            }
        } catch (Exception e) {
            responseCallBack.onException();
            Toaster.longToast(context.getResources().getString(R.string.oops_something_went_wrong));
            Applog.e(TAG, e.getMessage(), e);
        }
    }
    */
}
