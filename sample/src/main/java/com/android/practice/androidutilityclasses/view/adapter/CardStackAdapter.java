package com.android.practice.androidutilityclasses.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.android.practice.androidutilityclasses.R;
import com.android.practice.library.ImageUtils;

/**
 * Created by Aradh Pillaion 07/10/15.
 */

/*
* cardStackAdapter which is going to hold list of information and setting it into CardStack
*
* */
public class CardStackAdapter extends ArrayAdapter<String> {


    public CardStackAdapter(Context context, int resource) {
        super(context, 0);
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        //supply the layout for your card
        ImageView v = (ImageView) (contentView.findViewById(R.id.helloText));
        ImageUtils.displayImage(getContext(), getItem(position), v, R.drawable.ic_globe,
                R.drawable.ic_broken_file_colored);
        return contentView;
    }

}
