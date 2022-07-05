package com.example.fanp.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.annotation.Nullable;


public class Shabnamlight extends androidx.appcompat.widget.AppCompatTextView {


    @Override
    public boolean callOnClick() {
        return super.callOnClick();
    }

    public Shabnamlight(Context context) {
        super(context);
        Typeface face= Typeface.createFromAsset(context.getAssets(), "font/shabnamlight.ttf");
        this.setTypeface(face);
    }

    public Shabnamlight(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "font/shabnamlight.ttf");
        this.setTypeface(face);
    }

    public Shabnamlight(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "font/shabnamlight.ttf");
        this.setTypeface(face);
    }


}