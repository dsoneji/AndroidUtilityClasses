package com.android.practice.library.model;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Struct;

/**
 * Created by android.
 */

public class RandomDogModel {
    private String status;
    private String message;

    private static final String KEY_STATUS = "status";
    private static final String KEY_MESSAGE = "message";

    private JSONObject users = null;

    private String json;

    public RandomDogModel(String json){
        this.json = json;
    }

    public void parseJSON(){
        JSONObject jsonObject=null;
        try {
            jsonObject = new JSONObject(json);

            status = jsonObject.getString(KEY_STATUS);
            message = jsonObject.getString(KEY_MESSAGE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
