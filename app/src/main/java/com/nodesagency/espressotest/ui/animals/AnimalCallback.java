package com.nodesagency.espressotest.ui.animals;

import android.os.Vibrator;
import android.support.v4.app.Fragment;

/**
 * Created by liamakakpo on 15/07/2016.
 */
public interface AnimalCallback {

    void onButtonClicked(Fragment fragment);

    Vibrator getVibrator();
}
