package com.example.whatsapp.Utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.whatsapp.R;
import com.google.android.material.textfield.TextInputEditText;


public class $_Utils {
    public static void goToTargetActivity(AppCompatActivity source_activity, Class target_activity) {
        Intent intent = new Intent(source_activity, target_activity);
        source_activity.startActivity(intent);
    }

    public static void goToTargetActivityWithData(Context source_activity, Class target_activity, Bundle bundle) {
        Intent intent = new Intent(source_activity, target_activity);
        intent.putExtra("group_data", bundle);
        source_activity.startActivity(intent);
    }

    public static void goToTargetActivityWithFlag(AppCompatActivity source_activity, Class target_activity) {
        Intent intent = new Intent(source_activity, target_activity);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        source_activity.startActivity(intent);
        source_activity.finish();
    }

    public static void makeToast(Context context, String message, int length) {
        Toast.makeText(context, message, length).show();
    }

    public static ProgressDialog makeProgressDialog(Context context) {
        ProgressDialog progress_dialog;
        progress_dialog = new ProgressDialog(context);
        progress_dialog.setTitle("Creating account");
        progress_dialog.setMessage("Please wait, while creating new account for you...");
        progress_dialog.setCanceledOnTouchOutside(true);
        return progress_dialog;
    }

    protected void showInputDialog(Context context) {
        AlertDialog.Builder alert;
        final TextInputEditText input;
        alert = new AlertDialog.Builder(context);
        input = new TextInputEditText(context);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String value = input.getText().toString();
                // Do something with value!
            }
        });

        alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                // Canceled.
            }
        });
        alert.setIcon(R.drawable.username);
        alert.setMessage("Username");
        alert.setView(input);
        alert.show();
    }
}
