package com.nodesagency.espressotest.ui.monkey;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.nodesagency.espressotest.R;
import com.nodesagency.espressotest.ui.animals.AnimalPagerActivity;
import com.nodesagency.espressotest.util.AnimationUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by liamakakpo on 14/07/2016.
 */
public class MonkeyActivity extends Activity {

    /**
     * .-"-.            .-"-.            .-"-.           .-"-.
     * _/_-.-_\_        _/.-.-.\_        _/.-.-.\_       _/.-.-.\_
     * / __} {__ \      /|( o o )|\      ( ( o o ) )     ( ( o o ) )
     * / //  "  \\ \    | //  "  \\ |      |/  "  \|       |/  "  \|
     * / / \'---'/ \ \  / / \'---'/ \ \      \'/^\'/         \ .-. /
     * \ \_/`"""`\_/ /  \ \_/`"""`\_/ /      /`\ /`\         /`"""`\
     * \           /    \           /      /  /|\  \       /       \
     */

    @BindView(R.id.submitButton)
    Button submitButton;
    @BindView(R.id.powerSwitch)
    Switch powerSwitch;

    private Vibrator vibrator;

    boolean monkey = true;
    private int hitCount = 0;

    @OnClick(R.id.submitButton)
    public void onSubmitButtonClicked() {
        AnimationUtil.shakeView(submitButton);
        vibrator.vibrate(500);
        submitButton.setText(getString(monkey ? R.string.monkey_b : R.string.monkey_a));
        monkey = !monkey;
        hitCount = hitCount + 1;
        if (hitCount == 3) {
            Intent i = new Intent(this, AnimalPagerActivity.class);
            startActivity(i);
        }
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
