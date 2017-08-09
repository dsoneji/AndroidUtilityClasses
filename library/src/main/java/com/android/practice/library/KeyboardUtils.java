package com.android.practice.library;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * Created by android.
 */

public class KeyboardUtils {
    public static void showKeyboard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) CoreApp.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    public static void hideKeyboard(EditText editText) {
        InputMethodManager imm = (InputMethodManager) CoreApp.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
