package com.example.whatsapp.Utils;

import android.content.Context;
import android.content.Intent;


public class $Utils {
    public static void go_to_target_activity(Context source_activity, Class target_activity) {
        Intent intent = new Intent(source_activity, target_activity);
        source_activity.startActivity(intent);
    }
}
