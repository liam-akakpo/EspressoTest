package com.nodesagency.espressotest.util;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;


/**
 * Created by liamakakpo on 20/05/2016.
 */
public class DialogUtils {

    private DialogUtils() {

    }

    public static void showGenericDialog(@NonNull final Context context, @NonNull final String title, @NonNull final String message) {
        showGenericDialog(context, title, message, null);
    }

    public static void showGenericDialog(@NonNull final Context context, @NonNull final String title, @NonNull final String message, @Nullable DialogInterface.OnClickListener clickListener) {
        AlertDialog dialog = new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton("OK", clickListener)
                .create();
        dialog.show();
    }

}