package com.example.whatsapp.Utils;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;


public class $_Utils {
    public static void go_to_target_activity(Context source_activity, Class target_activity) {
        Intent intent = new Intent(source_activity, target_activity);
        source_activity.startActivity(intent);
    }

    public static void makeToast(Context context, String message, int length){
        Toast.makeText(context, message, length).show();

    }
}
