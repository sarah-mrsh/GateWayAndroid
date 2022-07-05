package com.example.fanp.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddressEdt extends androidx.appcompat.widget.AppCompatEditText {

    public boolean valid = false;



    TextWatcher watcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //YOUR CODE
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //YOUR CODE
        }

        @Override
        public void afterTextChanged(Editable s) {
            String outputedText = s.toString();
            if (outputedText.length() < 256 && outputedText.length() > 2) {
                if (validate(outputedText))
                    valid = (true);
            } else {
                valid = false;
            }
        }
    };

    public AddressEdt(@NonNull Context context) {
        super(context);
        addTextChangedListener(watcher);
    }

    public AddressEdt(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        addTextChangedListener(watcher);

    }

    public AddressEdt(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addTextChangedListener(watcher);


    }

    public boolean validate(final String hex) {

        return true;
//        Pattern pattern = Pattern.compile(Name_PATTERN);
//        Matcher matcher = pattern.matcher(hex);
//        if (matcher.find()) {
//            return true;
//        } else {
//            return false;
//            // TODO handle condition when input doesn't have an email address
//        }

    }


}
