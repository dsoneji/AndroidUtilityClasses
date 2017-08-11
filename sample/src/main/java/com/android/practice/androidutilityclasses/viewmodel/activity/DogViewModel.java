package com.android.practice.androidutilityclasses.viewmodel.activity;

import android.content.Context;
import android.os.Handler;
import android.view.View;

import com.android.practice.androidutilityclasses.R;
import com.android.practice.androidutilityclasses.view.activity.DogActivity;
import com.android.practice.androidutilityclasses.view.adapter.CardStackAdapter;
import com.android.practice.androidutilityclasses.viewmodel.CommonViewModel;
import com.android.practice.library.custom.cardstatck.cardstack.DefaultStackEventListener;
import com.android.practice.library.interf.SwipeListener;
import com.android.practice.library.interf.VolleyResponse;
import com.android.practice.library.model.RandomDogModel;
import com.android.practice.library.rest.APIManagerV;
import com.android.volley.VolleyError;

/**
 * Created by android.
 */

public class DogViewModel extends CommonViewModel {
    private CardStackAdapter mCardAdapter;
    private DogActivity mActivity;

    public DogViewModel(DogActivity activity) {
        super(activity);
        mActivity = activity;
        mCardAdapter = new CardStackAdapter(mActivity.getApplicationContext(), 0);
    }

    public void showProgressDialog(Context context) {
        mActivity.showProgressDialog(context);
    }

    public void hideProgressDialog() {
        mActivity.hideProgressDialog();
    }

    public void doRippleBackground() {
        startAnimation();
        //handler created to handle cardStack as well as timer...
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                callCardStack();
            }
        }, 8000);
    }

    private void callCardStack() {
        mActivity.getCardStackView().setVisibility(View.VISIBLE);
        mActivity.getRippleAnimationView().setVisibility(View.GONE);


        stopAnimation();

        //Setting Resource of CardStack
        mActivity.getCardStackView().setContentResource(R.layout.card_stack_item);

        //Adding 30 dummy info for CardStack
        addItem();
//        for (int i = 0; i <= 30; i++)
//            mCardAdapter.add("" + i);
//        mActivity.getCardStackView().setAdapter(mCardAdapter);

        //Setting Listener and passing distance as a parameter ,
        //based on the distance card will discard
        //if dragging card distance would be more than specified distance(100) then card will discard or else card will reverse on same position.
        mActivity.getCardStackView().setListener(new DefaultStackEventListener(300,
                new SwipeListener() {
                    @Override
                    public void onSwipe(int mIndex, int direction) {
                        addItem();
                    }
                }));
    }

    private void addItem() {
        APIManagerV.getInstance(mActivity).apiGetRandomDog
                (mActivity, new VolleyResponse() {
                    @Override
                    public void startProgress() {
                        showProgressDialog(mActivity);
                    }

                    @Override
                    public void onResponse(String response) {
                        hideProgressDialog();
                        if (!response.isEmpty()) {
                            RandomDogModel randomDogModel = new RandomDogModel(response);
                            randomDogModel.parseJSON();
                            mCardAdapter.add(randomDogModel.getMessage());
                            mActivity.getCardStackView().setAdapter(mCardAdapter);
                        }
                    }

                    @Override
                    public void onException(VolleyError error) {
                        hideProgressDialog();
                    }

                    @Override
                    public void onException() {
                        hideProgressDialog();
                    }
                });
    }

    private void startAnimation() {
        if (!mActivity.getRippleAnimationView().isRippleAnimationRunning()) {
            mActivity.getRippleAnimationView().startRippleAnimation();//start root ripple animation
        }
    }

    //this method will stop background ripple animation. if it's running.
    private void stopAnimation() {
        if (mActivity.getRippleAnimationView().isRippleAnimationRunning()) {
            mActivity.getRippleAnimationView().stopRippleAnimation();
        }
    }
}
