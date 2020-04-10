package com.example.whatsapp.Utils.Validation;

import android.view.View;

public abstract class $_Validator {
    public $_Validator(View view) {
        this.view = view;
    }

    protected View view;
    public abstract boolean validate();
}
