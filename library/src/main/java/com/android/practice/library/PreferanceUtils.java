package com.android.practice.library;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferanceUtils {

    public static final String PREFERENCE_NAME = "com.android.practice.library";
    private static final String SIGN_IN_FOR_CHAT = PREFERENCE_NAME + ".sample.preference";

    /**
     * Insert string value in Shared Preferences
     *
     * @param context of application
     * @param value   to store in preferences
     * @param key     using which value is mapped
     * @return
     */
    public static boolean putStringInPreferences(final Context context,
                                                 final String value, final String key) {
        final SharedPreferences sharedPreferences = context
                .getSharedPreferences(PREFERENCE_NAME,
                        Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
        return true;
    }

    /**
     * Get Data from preferance
     *
     * @param context
     * @param defaultValue
     * @param key
     * @return
     */
    public static String getStringFromPreferences(final Context context,
                                                  final String defaultValue, final String key) {
        final SharedPreferences sharedPreferences = context
                .getSharedPreferences(PREFERENCE_NAME,
                        Context.MODE_PRIVATE);
        final String temp = sharedPreferences.getString(key, defaultValue);
        return temp;
    }

    /**
     * Insert booblean in preferance
     *
     * @param context
     * @param value
     * @param key
     * @return
     */
    public static boolean putBooleanInPreferences(final Context context,
                                                  final boolean value, final String key) {
        final SharedPreferences sharedPreferences = context
                .getSharedPreferences(PREFERENCE_NAME,
                        Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
        return true;
    }

    /**
     * Get boolean from preferance
     *
     * @param context
     * @param defaultValue
     * @param key
     * @return
     */
    public static boolean getBooleanFromPreferences(final Context context,
                                                    final boolean defaultValue, final String key) {
        final SharedPreferences sharedPreferences = context
                .getSharedPreferences(PREFERENCE_NAME,
                        Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, defaultValue);
    }

    /**
     * Insert integer value in preferences
     *
     * @param context
     * @param value
     * @param key
     * @return
     */
    public static boolean putIntegerInPreferences(final Context context,
                                                  final int value, final String key) {
        final SharedPreferences sharedPreferences = context
                .getSharedPreferences(PREFERENCE_NAME,
                        Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
        return true;
    }

    /**
     * Return integer preference value
     *
     * @param context
     * @param defaultValue
     * @param key
     * @return
     */
    public static int getIntegerFromPreferences(final Context context,
                                                final int defaultValue, final String key) {
        final SharedPreferences sharedPreferences = context
                .getSharedPreferences(PREFERENCE_NAME,
                        Context.MODE_PRIVATE);
        final int temp = sharedPreferences.getInt(key, defaultValue);
        return temp;
    }

    public static void setSamplePreference(Context context, String value) {
        PreferanceUtils.putStringInPreferences(context, value, SIGN_IN_FOR_CHAT);
    }

    public static String getSamplePreference(Context context) {
        return PreferanceUtils.getStringFromPreferences(context, "", SIGN_IN_FOR_CHAT);
    }

    /**
     * Clear Data From Preference
     *
     * @param context
     */
    public static void clearPreference(Context context) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
    }
}
