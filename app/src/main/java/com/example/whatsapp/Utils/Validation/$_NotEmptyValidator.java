package com.example.whatsapp.Utils.Validation;

import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

public class $_NotEmptyValidator extends $_Validator {
    public $_NotEmptyValidator(View view) {
        super(view);
    }

    @Override
    public boolean validate() {
        if (((TextInputEditText) this.view).getText().toString().isEmpty()) {
            ((TextInputEditText) this.view).setError("Must be not empty");
            return false;
        }
        return true;
    }
}
