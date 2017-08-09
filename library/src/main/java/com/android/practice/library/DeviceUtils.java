package com.android.practice.library;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by android.
 */

public class DeviceUtils {
    private static final String TAG = DeviceUtils.class.getSimpleName();

    public static String getDeviceUid() {
        Context context = CoreApp.getInstance();
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String uniqueDeviceId = telephonyManager.getDeviceId();
        if (TextUtils.isEmpty(uniqueDeviceId)) {
            ContentResolver cr = context.getContentResolver();
            uniqueDeviceId = Settings.Secure.getString(cr, Settings.Secure.ANDROID_ID);
        }

        return uniqueDeviceId;
    }

    public static int getDeviceWidth(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        return width;
    }

    public static int getDeviceHeight(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        return height;
    }

    public static void getHashKeyOfYourProject(Activity mActivity) {
        try {
            PackageInfo info = mActivity.getPackageManager().getPackageInfo(
                    mActivity.getPackageName(),
                    PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());

                Applog.d(TAG, "Getting Key Hash Issue With Face book put this Key On facebook");
                Applog.d(TAG, "Key Hash = " + Base64.encodeToString(md.digest(),
                        Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Applog.e(TAG, e.getMessage(), e);
        } catch (NoSuchAlgorithmException e) {
            Applog.e(TAG, e.getMessage(), e);
        }
    }

    public static boolean isAppRunning(final Context context, final String packageName) {
        final ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningAppProcessInfo> procInfos = activityManager.getRunningAppProcesses();
        if (procInfos != null) {
            for (final ActivityManager.RunningAppProcessInfo processInfo : procInfos) {
                if (processInfo.processName.equals(packageName)) {
                    return true;
                }
            }
        }
        return false;
    }
}
