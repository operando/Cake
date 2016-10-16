package com.os.operando.cake;

import android.databinding.BindingAdapter;
import android.util.TypedValue;
import android.widget.TextView;

public final class DataBindingAttribute {

    private DataBindingAttribute() {
    }

    @BindingAdapter("memoTextSize")
    public static void setMemoTextSize(TextView textView, float textSize) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }
}
