package com.android.practice.library;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by android.
 */

public class FontUtils {
    public static Typeface getRobotoRegular(Context context) {
        return Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular.ttf");
    }
}
