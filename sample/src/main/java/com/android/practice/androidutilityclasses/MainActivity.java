package com.android.practice.androidutilityclasses;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.practice.androidutilityclasses.view.CommonAppCompatActivity;
import com.android.practice.library.Applog;
import com.android.practice.library.PermissionUtils;
import com.android.practice.library.Toaster;
import com.android.practice.library.custom.GPSTracker;
import com.android.practice.library.interf.ApiProgressHelper;
import com.android.practice.library.interf.VolleyResponse;
import com.android.practice.library.model.Example;
import com.android.practice.library.rest.APIManagerR;
import com.android.practice.library.rest.APIManagerV;
import com.android.volley.VolleyError;
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
        ((TextView) findViewById(R.id.txtResultVolley))
                .setMovementMethod(new ScrollingMovementMethod());
        ((TextView) findViewById(R.id.txtResultRetrofit))
                .setMovementMethod(new ScrollingMovementMethod());
        if (PermissionUtils.hasPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            mGpsTracker = new GPSTracker(this);
        } else {
            String permission[] = {android.Manifest.permission.ACCESS_FINE_LOCATION};
            PermissionUtils.requestPermissions(MainActivity.this, permission, 1002);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
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
                        APIManagerR.getInstance().apiWeatherByLatLong(MainActivity.this,
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


                        APIManagerV.getInstance(MainActivity.this)
                                .apiWeatherByLatLongV(MainActivity.this,
                                        new VolleyResponse() {
                                            @Override
                                            public void startProgress() {
                                                showProgressDialog(MainActivity.this);
                                            }

                                            @Override
                                            public void onResponse(String response) {
                                                hideProgressDialog();
                                                displayText(2, response);
                                                Applog.d(TAG, response);
                                            }

                                            @Override
                                            public void onException(VolleyError error) {
                                                Applog.e(TAG, error.getMessage(), error);
                                                Toaster.longToast(getResources().getString(R.string.oops_something_went_wrong));
                                                hideProgressDialog();
                                            }

                                            @Override
                                            public void onException() {
                                                hideProgressDialog();
                                            }
                                        },
                                        String.valueOf(mGpsTracker.getLatitude()),
                                        String.valueOf(mGpsTracker.getLongitude()));

                    } else {
                        mGpsTracker.showSettingsAlert();
                    }
                } else {
                    String permission[] = {android.Manifest.permission.ACCESS_FINE_LOCATION};
                    PermissionUtils.requestPermissions(MainActivity.this, permission, 1002);
                }
                break;

            case R.id.itemAllPost:
                APIManagerV.getInstance(MainActivity.this)
                        .apiGetPostsData(MainActivity.this, new VolleyResponse() {
                            @Override
                            public void startProgress() {
                                showProgressDialog(MainActivity.this);
                            }

                            @Override
                            public void onResponse(String response) {
                                hideProgressDialog();
                                displayText(2, response);
                                Applog.d(TAG, response);
                            }

                            @Override
                            public void onException(VolleyError error) {
                                Applog.e(TAG, error.getMessage(), error);
                                Toaster.longToast(getResources().getString(R.string.oops_something_went_wrong));
                                hideProgressDialog();
                            }

                            @Override
                            public void onException() {
                                hideProgressDialog();
                            }
                        });
                break;

            case R.id.itemSpecificPost:
                APIManagerV.getInstance(MainActivity.this)
                        .apiGetSpecificPostData(MainActivity.this, new VolleyResponse() {
                            @Override
                            public void startProgress() {
                                showProgressDialog(MainActivity.this);
                            }

                            @Override
                            public void onResponse(String response) {
                                hideProgressDialog();
                                displayText(2, response);
                                Applog.d(TAG, response);
                            }

                            @Override
                            public void onException(VolleyError error) {
                                Applog.e(TAG, error.getMessage(), error);
                                Toaster.longToast(getResources().getString(R.string.oops_something_went_wrong));
                                hideProgressDialog();
                            }

                            @Override
                            public void onException() {
                                hideProgressDialog();
                            }
                        });
                break;
        }
        return true;
    }

    Callback<Example> mCallback = new Callback<Example>() {
        @Override
        public void onResponse(Call<Example> call,
                               Response<Example> response) {
            hideProgressDialog();
            if (response.body().getError() != null) {
                Toaster.longToast(getResources().getString(R.string.oops_something_went_wrong));
                return;
            }
            displayText(1, response.body().toString());
//            findViewById(R.id.txtResult).requestLayout();
            Applog.d(TAG, new Gson().toJson(response.body()));
        }

        @Override
        public void onFailure(Call<Example> call, Throwable t) {
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

    @Override
    protected void onStop() {
        super.onStop();
        APIManagerV.getInstance(MainActivity.this).stopAllVolleyRequest();
    }

    public void displayText(int which, String text){
        switch (which){
            case 1:
                ((TextView) findViewById(R.id.txtResultRetrofit)).setText(text);
                findViewById(R.id.txtResultRetrofit).requestLayout();
                break;
            case 2:
                ((TextView) findViewById(R.id.txtResultVolley)).setText(text);
                findViewById(R.id.txtResultVolley).requestLayout();
                break;
        }
    }
}
