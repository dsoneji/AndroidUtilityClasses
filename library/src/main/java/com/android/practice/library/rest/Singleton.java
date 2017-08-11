package com.android.practice.library.rest;

/**
 * Created by Riontech-5 on 9/1/17.
 */
public class Singleton {
    private final static String TAG = Singleton.class.getSimpleName();
    private static final Object LOCK = new Object();
    private static FinalWrapper<Singleton> mHelperWrapper;
    private final RestClient mRestAuthClient1, mRestAuthClient2;

    private Singleton() {
        mRestAuthClient1 = ServiceGenerator.createAuthService(RestClient.class);
        mRestAuthClient2 = ServiceGenerator.createAuthService2(RestClient.class);
    }

    public static Singleton getInstance() {
        FinalWrapper<Singleton> wrapper = mHelperWrapper;

        if (wrapper == null) {
            synchronized (LOCK) {
                if (mHelperWrapper == null) {
                    mHelperWrapper = new FinalWrapper<>(new Singleton());
                }
                wrapper = mHelperWrapper;
            }
        }
        return wrapper.getValue();
    }

    public void stopRunningAPI() {
        ServiceGenerator.stopRunningAPI();
    }

    public RestClient getRestAuthClient() {
        return mRestAuthClient1;
    }

    public RestClient getRestAuthClient2() {
        return mRestAuthClient2;
    }
}
