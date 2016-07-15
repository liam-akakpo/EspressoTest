package com.nodesagency.espressotest.ui.animals;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.nodesagency.espressotest.R;
import com.nodesagency.espressotest.util.AnimationUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liamakakpo on 15/07/2016.
 */
public class ThirdFragment extends Fragment {

    @BindView(R.id.animal3_1)
    Button animal1;
    @BindView(R.id.animal3_2)
    Button animal2;
    @BindView(R.id.animal3_3)
    Button animal3;

    @OnClick(R.id.animal3_1)
    public void onAnimalOneClick() {
        AnimationUtil.shakeView(animal1);
        callback.getVibrator().vibrate(50);
    }

    @OnClick(R.id.animal3_2)
    public void onAnimalTwoClick() {
        AnimationUtil.shakeView(animal2);
        callback.getVibrator().vibrate(50);
        callback.onButtonClicked(this);
    }

    @OnClick(R.id.animal3_3)
    public void onAnimalThreeClick() {
        AnimationUtil.shakeView(animal3);
        callback.getVibrator().vibrate(50);
    }

    private AnimalCallback callback;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.callback = (AnimalCallback) activity;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_three_animals, container, false);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

}
