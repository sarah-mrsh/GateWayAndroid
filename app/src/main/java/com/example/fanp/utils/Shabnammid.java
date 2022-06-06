package com.example.fanp.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;


@SuppressLint("AppCompatCustomView")
public class Shabnammid extends TextView {


    public Shabnammid(Context context) {
        super(context);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "font/shabnammid.ttf");
        this.setTypeface(face);
    }

    public Shabnammid(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "font/shabnammid.ttf");
        this.setTypeface(face);
    }

    public Shabnammid(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "font/shabnammid.ttf");
        this.setTypeface(face);
    }

    protected void onDraw (Canvas canvas) {
        super.onDraw(canvas);


    }

}