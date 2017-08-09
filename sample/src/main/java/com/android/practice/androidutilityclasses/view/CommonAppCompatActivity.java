package com.android.practice.androidutilityclasses.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.practice.library.Applog;
import com.android.practice.library.DialogUtils;

/**
 * Created by android.
 */

public class CommonAppCompatActivity extends AppCompatActivity {
    private static final String TAG = DialogUtils.class.getSimpleName();
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public void showProgressDialog(Context context) {
        try {
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(context);
                progressDialog.setMessage(context.getResources().getString(com.android.practice.library.R.string.lbl_processing_progress_dialog));
                progressDialog.setTitle(context.getResources().getString(com.android.practice.library.R.string.app_name));
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
            } else {
                if (!progressDialog.isShowing()) {
                    progressDialog = new ProgressDialog(context);
                    progressDialog.setMessage(context.getResources().getString(com.android.practice.library.R.string.lbl_processing_progress_dialog));
                    progressDialog.setTitle(context.getResources().getString(com.android.practice.library.R.string.app_name));
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.show();
                }
            }
        } catch (Exception e) {
            Applog.e(TAG, e.getMessage(), e);
        }
    }

    public void hideProgressDialog() {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        } catch (Exception e) {
            Applog.e(TAG, e.getMessage(), e);
        }
    }
}
