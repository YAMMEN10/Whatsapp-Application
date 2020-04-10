package com.example.whatsapp.Utils.Validation;

import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

public class $_EmailValidator extends $_Validator {

    public $_EmailValidator(View view) {
        super(view);
    }

    @Override
    public boolean validate() {
        if (!Patterns.EMAIL_ADDRESS.matcher(((EditText)this.view).getText()).matches()) {
            ((EditText)this.view).setError("Enter a valid email");
            return  false;
        }
        return true;
    }
}
