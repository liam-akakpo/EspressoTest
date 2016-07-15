package com.nodesagency.espressotest;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liamakakpo on 14/07/2016.
 */
public class MonkeyActivity extends Activity {

    @BindView(R.id.submitButton)
    Button submitButton;
    @BindView(R.id.powerSwitch)
    Switch powerSwitch;

    private Vibrator vibrator;

    boolean monkey = true;

    @OnClick(R.id.submitButton)
    public void onSubmitButtonClicked() {
        ObjectAnimator
                .ofFloat(submitButton, "translationX", 0, 25, -25, 25, -25, 15, -15, 6, -6, 0)
                .setDuration(500)
                .start();
        vibrator.vibrate(500);
        submitButton.setText(getString(monkey ? R.string.monkey_b : R.string.monkey_a));
        monkey = !monkey;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monkey);
        ButterKnife.bind(this);
        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        powerSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                submitButton.setEnabled(isChecked);
            }
        });
    }

}
