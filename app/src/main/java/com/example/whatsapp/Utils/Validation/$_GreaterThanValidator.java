package com.example.whatsapp.Utils.Validation;

import android.view.View;
import android.widget.EditText;

public class $_GreaterThanValidator extends $_Validator {
    private int limit;

    public $_GreaterThanValidator(View view, int limit) {
        super(view);
        this.limit = limit;
    }

    @Override
    public boolean validate() {
        if (((EditText) this.view).getText().length() < this.limit) {
            ((EditText) this.view).setError("Password must larger than 6 character");
            return false;
        }
        return true;

    }
}
