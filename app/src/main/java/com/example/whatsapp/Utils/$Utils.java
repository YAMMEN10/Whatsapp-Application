package com.example.whatsapp.Utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.whatsapp.Activities.SignupActivity.SignupActivity;


public class $Utils {
    public static void go_to_target_activity(Context source_activity, Class target_activity) {
        Intent intent = new Intent(source_activity, target_activity);
        source_activity.startActivity(intent);
    }

    public static void makeToast(Context context, String message, int length){
        Toast.makeText(context, message, length).show();

    }
}
