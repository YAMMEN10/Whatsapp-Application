package com.example.whatsapp.Utils.Validation;

import android.view.View;
import android.widget.EditText;

public class $_NotEmptyValidator extends $_Validator {
    public $_NotEmptyValidator(View view) {
        super(view);
    }

    @Override
    public boolean validate() {
        if (((EditText) this.view).getText().toString().isEmpty()) {
            ((EditText) this.view).setError("Enter a valid email");
            return false;
        }
        return true;
    }
}
