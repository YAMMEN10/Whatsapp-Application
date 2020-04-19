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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


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

    public static ProgressDialog makeProgressDialog(Context context,String title, String message) {
        ProgressDialog progress_dialog;
        progress_dialog = new ProgressDialog(context);
        progress_dialog.setTitle(title);
        progress_dialog.setMessage(message);
        progress_dialog.setCanceledOnTouchOutside(true);
        return progress_dialog;
    }

    public static String getDate(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simple_date_format = new SimpleDateFormat("MMM dd, yy", Locale.ENGLISH);
        return simple_date_format.format(calendar.getTime());
    }


    public static String getTime(){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simple_date_format = new SimpleDateFormat("hh:mm a",Locale.ENGLISH);
        return simple_date_format.format(calendar.getTime());
    }
}
