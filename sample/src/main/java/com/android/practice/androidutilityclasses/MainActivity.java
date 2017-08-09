package com.android.practice.androidutilityclasses;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.android.practice.androidutilityclasses.view.CommonAppCompatActivity;
import com.android.practice.library.Applog;
import com.android.practice.library.PermissionUtils;
import com.android.practice.library.Toaster;
import com.android.practice.library.custom.GPSTracker;
import com.android.practice.library.interf.ApiProgressHelper;
import com.android.practice.library.mode.Example;
import com.android.practice.library.mode.ServerResponse;
import com.android.practice.library.rest.APIManager;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends CommonAppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private GPSTracker mGpsTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (PermissionUtils.hasPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            mGpsTracker = new GPSTracker(this);
        } else {
            String permission[] = {android.Manifest.permission.ACCESS_FINE_LOCATION};
            PermissionUtils.requestPermissions(MainActivity.this, permission, 1002);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu1, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.locationMenu:
                if (PermissionUtils.hasPermission(MainActivity.this,
                        Manifest.permission.ACCESS_FINE_LOCATION)) {
                    if (mGpsTracker.getIsGPSTrackingEnabled()) {
                        APIManager.getInstance().apiWeatherByLatLong(MainActivity.this,
                                String.valueOf(mGpsTracker.getLatitude()),
                                String.valueOf(mGpsTracker.getLongitude()), mCallback,
                                new ApiProgressHelper() {
                                    @Override
                                    public void startProgress() {
                                        showProgressDialog(MainActivity.this);
                                    }

                                    @Override
                                    public void onException() {
                                        hideProgressDialog();
                                    }
                                });
                    } else {
                        mGpsTracker.showSettingsAlert();
                    }
                } else {
                    String permission[] = {android.Manifest.permission.ACCESS_FINE_LOCATION};
                    PermissionUtils.requestPermissions(MainActivity.this, permission, 1002);
                }
                break;
        }
        return true;
    }

    Callback<ServerResponse<Example>> mCallback = new Callback<ServerResponse<Example>>() {
        @Override
        public void onResponse(Call<ServerResponse<Example>> call,
                               Response<ServerResponse<Example>> response) {
            hideProgressDialog();
            Applog.d(TAG, new Gson().toJson(response.body()));
        }

        @Override
        public void onFailure(Call<ServerResponse<Example>> call, Throwable t) {
            Toaster.longToast(getResources().getString(R.string.oops_something_went_wrong));
            hideProgressDialog();
        }
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1002 && mGpsTracker == null) {
            mGpsTracker = new GPSTracker(MainActivity.this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (PermissionUtils.hasPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            mGpsTracker = new GPSTracker(this);
        } else {
            String permission[] = {android.Manifest.permission.ACCESS_FINE_LOCATION};
            PermissionUtils.requestPermissions(MainActivity.this, permission, 1002);
        }
    }
}
