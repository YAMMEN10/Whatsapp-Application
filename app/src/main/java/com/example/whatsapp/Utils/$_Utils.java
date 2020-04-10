package com.example.whatsapp.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;


public class $_Utils {
    public static void goToTargetActivity(AppCompatActivity source_activity, Class target_activity) {
        Intent intent = new Intent(source_activity, target_activity);
        source_activity.startActivity(intent);
    }

    public static void goToTargetActivityWithFlag(AppCompatActivity source_activity, Class target_activity) {
        Intent intent = new Intent(source_activity, target_activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        source_activity.startActivity(intent);
        source_activity.finish();
    }

    public static void makeToast(Context context, String message, int length){
        Toast.makeText(context, message, length).show();
    }

    public static ProgressDialog makeProgressDialog(Context context){
        ProgressDialog progress_dialog;
        progress_dialog = new ProgressDialog(context);
        progress_dialog.setTitle("Creating account");
        progress_dialog.setMessage("Please wait, while creating new account for you...");
        progress_dialog.setCanceledOnTouchOutside(true);
        return progress_dialog;
    }
}
