package com.android.practice.library.interf;

import com.android.volley.Response;

/**
 * Created by android.
 */

public interface VolleyResponse {
    void onResponse(Response.Listener<String> responseCallBack);
    void onException(Response.ErrorListener errorCallBack);
}
