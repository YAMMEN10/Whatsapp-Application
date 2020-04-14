package com.example.whatsapp.Utils;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

import com.example.whatsapp.R;
import com.google.android.material.textfield.TextInputEditText;

public class InputDialog extends AlertDialog.Builder {
    private TextInputEditText input;

    public InputDialog(Context context, String message) {
        super(context);
        input = new TextInputEditText(context);
        this.setIcon(R.drawable.username);
        this.setMessage(message);
        this.setView(input);
    }


    public TextInputEditText getInput() {
        return input;
    }
}
