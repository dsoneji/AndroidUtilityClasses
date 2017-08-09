package com.android.practice.library.rest;

/**
 * Created by Riontech-5 on 9/1/17.
 */
public class Singleton {
    private final static String TAG = Singleton.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static FinalWrapper<Singleton> helperWrapper;
    private final RestClient mRestAuthClient;

    private Singleton() {
        mRestAuthClient = ServiceGenerator.createAuthService(RestClient.class);
    }

    public static Singleton getInstance() {
        FinalWrapper<Singleton> wrapper = helperWrapper;

        if (wrapper == null) {
            synchronized (LOCK) {
                if (helperWrapper == null) {
                    helperWrapper = new FinalWrapper<>(new Singleton());
                }
                wrapper = helperWrapper;
            }
        }
        return wrapper.getValue();
    }

    public void stopRunningAPI() {
        ServiceGenerator.stopRunningAPI();
    }

    public RestClient getRestAuthClient() {
        return mRestAuthClient;
    }
}
