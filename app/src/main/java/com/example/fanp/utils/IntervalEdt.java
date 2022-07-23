package com.example.fanp.utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fanp.R;

import java.util.regex.Pattern;

public class IntervalEdt extends androidx.appcompat.widget.AppCompatEditText {

    public boolean valid = false;




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
            if (Integer.parseInt(outputedText)>100 && Integer.parseInt(outputedText) < 3600000 ) {
                valid=true;
            }else
                valid=false;
        }
    };

    public IntervalEdt(@NonNull Context context) {
        super(context);
        addTextChangedListener(watcher);
//        setInputType(InputType.TYPE_CLASS_NUMBER);
//        setFilters(filters());

    }

    public IntervalEdt(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        addTextChangedListener(watcher);
//        setInputType(InputType.TYPE_CLASS_NUMBER);
//        setFilters(filters());

    }

    public IntervalEdt(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        addTextChangedListener(watcher);
//        setInputType(InputType.TYPE_CLASS_NUMBER);
//        setFilters(filters());
    }



}
