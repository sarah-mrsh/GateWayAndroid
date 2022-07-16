package com.example.fanp.utils;

import android.content.Context;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fanp.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpEdt extends androidx.appcompat.widget.AppCompatEditText {

    public boolean valid = false;

    private static final String IPV4_REGEX =
            "^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\." +
                    "(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";

    private static final Pattern IPv4_PATTERN = Pattern.compile(IPV4_REGEX);


    @Override
    public void setTag(Object tag) {
        super.setTag(tag);
    }

    @Override
    public Object getTag() {
        return super.getTag();

    }

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
            if (outputedText.length() <= 20 && outputedText.length() > 2) {
                if (isValidInet4Address(outputedText)){
                    valid = (true);
                    setTextColor(getResources().getColor(R.color.white));
                }else{
                    valid = false;
                    setTextColor(getResources().getColor(R.color.main_yellow));
                }

            } else {
                valid = false;
                setTextColor(getResources().getColor(R.color.main_yellow));
            }
        }
    };

    public IpEdt(@NonNull Context context) {
        super(context);
        addTextChangedListener(watcher);
//        setInputType(InputType.TYPE_CLASS_NUMBER);
//        setFilters(filters());

    }

    public IpEdt(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        addTextChangedListener(watcher);
//        setInputType(InputType.TYPE_CLASS_NUMBER);
//        setFilters(filters());

    }

    public IpEdt(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addTextChangedListener(watcher);
//        setInputType(InputType.TYPE_CLASS_NUMBER);
//        setFilters(filters());
    }

    public static boolean isValidInet4Address(String ip)
    {
        if (ip == null) {
            return false;
        }

        if (!IPv4_PATTERN.matcher(ip).matches()) {
            return false;
        }

        String[] parts = ip.split("\\.");

        // verify that each of the four subgroups of IPv4 addresses is legal
        try {
            for (String segment: parts)
            {
                // x.0.x.x is accepted but x.01.x.x is not
                if (Integer.parseInt(segment) > 255 ||
                        (segment.length() > 1 && segment.startsWith("0"))) {
                    return false;
                }
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }


}
