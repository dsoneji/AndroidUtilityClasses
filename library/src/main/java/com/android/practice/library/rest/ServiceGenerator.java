package com.android.practice.library.rest;

import com.android.practice.library.AppConstant;
import com.android.practice.library.Applog;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    private static final String TAG = ServiceGenerator.class.getSimpleName();
    private static OkHttpClient okHttpClient;

    public ServiceGenerator() {
    }


    public static <S> S createAuthService(Class<S> restClientClass) {
        // set your desired log level
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //add interceptor to OkHttpClient
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.addInterceptor(httpLoggingInterceptor);
        builder.interceptors().add(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request().newBuilder()
//                        .addHeader("Content-Type", "application/json")
//                        .addHeader("X-Auth", AppConstant.X_AUTH_VALUE)
                        .build();
                return chain.proceed(request);
            }
        });

        builder.readTimeout(AppConstant.CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS).
                connectTimeout(AppConstant.CONNECTION_TIMEOUT, TimeUnit.MILLISECONDS);
        okHttpClient = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(AppConstant.BASE_URL_OPEN_WEATHER_MAP)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(restClientClass);
    }

    public static void stopRunningAPI() {
        if (okHttpClient != null) {
            if (okHttpClient.dispatcher().runningCallsCount() > 0) {
                okHttpClient.dispatcher().cancelAll();
                Applog.e(TAG, "-------stopRunningAPI------------");
            }
        }
    }
}
