package com.nodesagency.espressotest.util;

import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.view.View;

import com.nodesagency.espressotest.R;

/**
 * Created by liamakakpo on 15/07/2016.
 */
public class AnimationUtil {

    private AnimationUtil () {

    }

    public static void shakeView(@NonNull final View view ) {
        ObjectAnimator
                .ofFloat(view, "translationX", 0, 25, -25, 25, -25, 15, -15, 6, -6, 0)
                .setDuration(500)
                .start();

    }

}
