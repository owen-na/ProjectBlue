package com.example.searchprov01;

import android.widget.EditText;
import android.widget.Toast;

public abstract class InputChecker extends AppCompatActivity {

    protected abstract void allValid();

    protected String stringify(EditText editText) {
        return editText.getText().toString().trim();
    }

    protected void createToast(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    protected boolean stringValid(String str, String blankError) {
        boolean isEmpty = str.isEmpty();
        if (isEmpty) {
            createToast(blankError);
        }
        return isEmpty;
    }

    protected boolean intValid(String str, String numError, String nanError) {
        try {
            int i = Integer.parseInt(str);
            if (i <= 0) {
                createToast(numError);
            }
            return i > 0;
        } catch (NumberFormatException e) {
            createToast(nanError);
            return false;
        }
    }

    protected boolean doubleValid(String str, String numError, String nanError) {
        try {
            double d = Double.parseDouble(str);
            if (d <= 0.0) {
                createToast(numError);                
            }
            return d > 0.0;
        } catch (NumberFormatException e) {
            createToast(nanError);
            return false;
        }
    }
}