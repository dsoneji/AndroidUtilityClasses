package com.android.practice.androidutilityclasses.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.android.practice.androidutilityclasses.R;
import com.android.practice.androidutilityclasses.view.CommonAppCompatActivity;
import com.android.practice.androidutilityclasses.viewmodel.activity.DogViewModel;
import com.android.practice.library.custom.RippleAnimation;
import com.android.practice.library.custom.cardstatck.cardstack.CardStack;

/**
 * Created by android.
 */

public class DogActivity extends CommonAppCompatActivity {
    private DogViewModel mViewModel;
    private RippleAnimation mRippleAnimation;
    private CardStack mCardStack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dogs);
        mViewModel = new DogViewModel(this);
        mRippleAnimation = (RippleAnimation) findViewById(R.id.ripple_layout);
        mCardStack = (CardStack) findViewById(R.id.frame);

        mRippleAnimation.setVisibility(View.VISIBLE);
        mCardStack.setVisibility(View.GONE);

        mViewModel.doRippleBackground();
    }

    public RippleAnimation getRippleAnimationView() {
        return mRippleAnimation;
    }

    public CardStack getCardStackView() {
        return mCardStack;
    }
}
