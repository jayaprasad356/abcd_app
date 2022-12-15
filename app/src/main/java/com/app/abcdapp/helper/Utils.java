package com.app.abcdapp.helper;


import android.annotation.SuppressLint;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.widget.EditText;

import com.app.abcdapp.R;


public class Utils {

    @SuppressLint("ClickableViewAccessibility")
    public static void setHideShowPassword(final EditText edtPassword) {
        edtPassword.setTag("show");
        edtPassword.setOnTouchListener((v, event) -> {
            final int DRAWABLE_RIGHT = 2;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (edtPassword.getRight() - edtPassword.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                    if (edtPassword.getTag().equals("show")) {
                        edtPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_hide, 0);
                        edtPassword.setTransformationMethod(null);
                        edtPassword.setTag("hide");
                    } else {
                        edtPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_show, 0);
                        edtPassword.setTransformationMethod(new PasswordTransformationMethod());
                        edtPassword.setTag("show");
                    }
                    return true;
                }
            }
            return false;
        });
    }
}
