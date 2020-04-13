package com.example.whatsapp.Utils.Validation;

import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

public class $_GreaterThanValidator extends $_Validator {
    private int limit;

    public $_GreaterThanValidator(View view, int limit) {
        super(view);
        this.limit = limit;
    }

    @Override
    public boolean validate() {
        if (((TextInputEditText) this.view).getText().length() < this.limit) {
            ((TextInputEditText) this.view).setError("Input must larger than 6 character");
            return false;
        }
        return true;

    }
}
