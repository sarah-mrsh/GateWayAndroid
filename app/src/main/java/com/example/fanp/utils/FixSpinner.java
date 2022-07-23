package com.example.fanp.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Spinner;

public class FixSpinner extends Spinner {

    public static boolean forc_disable = false;

    public FixSpinner(Context context) {
        super(context);
    }

    public FixSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixSpinner(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void
    setSelection(int position, boolean animate) {
        if (!forc_disable) {
            boolean sameSelected = position == getSelectedItemPosition();
            super.setSelection(position, animate);
            if (sameSelected) {
                // Spinner does not call the OnItemSelectedListener if the same item is selected, so do it manually now
                getOnItemSelectedListener().onItemSelected(this, getSelectedView(), position, getSelectedItemId());
            }
        }
    }

    @Override
    public void
    setSelection(int position) {
        if (!forc_disable) {
            boolean sameSelected = position == getSelectedItemPosition();
            super.setSelection(position);
            if (sameSelected) {
                // Spinner does not call the OnItemSelectedListener if the same item is selected, so do it manually now
                getOnItemSelectedListener().onItemSelected(this, getSelectedView(), position, getSelectedItemId());
            }
        }

    }
}