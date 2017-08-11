package com.android.practice.library.interf;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by android.
 */

public interface VolleyResponse {
    void startProgress();
    void onResponse(String response);
    void onException(VolleyError error);
    void onException();
}
