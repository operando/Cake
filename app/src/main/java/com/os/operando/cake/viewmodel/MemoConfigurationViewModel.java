package com.os.operando.cake.viewmodel;

import android.databinding.BindingAdapter;
import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.databinding.ObservableInt;
import android.util.TypedValue;
import android.widget.TextView;

public class MemoConfigurationViewModel {

    public final ObservableField<String> memo = new ObservableField<>();
    public final ObservableFloat textSize = new ObservableFloat();
    public final ObservableInt backgroundColor = new ObservableInt();

    private final float minTextSize;

    public MemoConfigurationViewModel(float minTextSize) {
        this.minTextSize = minTextSize;
        textSize.set(minTextSize);
    }

    public void onTextSizeChanged(int progress) {
        textSize.set(minTextSize + progress);
    }

    @BindingAdapter("memoTextSize")
    public static void setMemoTextSize(TextView textView, float textSize) {
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
    }
}