package com.os.operando.cake.viewmodel;

import android.databinding.ObservableField;
import android.databinding.ObservableFloat;
import android.util.TypedValue;
import android.widget.TextView;

public class MemoConfigurationViewModel {

    public final ObservableField<String> memo = new ObservableField<>();
    public final ObservableFloat textSize = new ObservableFloat();

    private final float minTextSize;

    public MemoConfigurationViewModel(float minTextSize) {
        this.minTextSize = minTextSize;
        textSize.set(minTextSize);
    }

    public void onTextSizeChanged(int progress) {
        textSize.set(minTextSize + progress);
    }

}