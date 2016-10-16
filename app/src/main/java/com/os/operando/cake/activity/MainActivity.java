package com.os.operando.cake.activity;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.os.operando.cake.R;
import com.os.operando.cake.appwidget.MemoAppWidget;
import com.os.operando.cake.databinding.ActivityMainBinding;
import com.os.operando.cake.viewmodel.MemoConfigurationViewModel;
import com.pavelsikun.vintagechroma.ChromaDialog;
import com.pavelsikun.vintagechroma.OnColorSelectedListener;
import com.pavelsikun.vintagechroma.colormode.ColorMode;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MemoConfigurationViewModel viewModel;

    private int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    private int backgroundColor;

    public static Intent createIntent(Context context) {
        Intent i = new Intent(context, MainActivity.class);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setResult(RESULT_CANCELED);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            appWidgetId = bundle.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }

        viewModel = new MemoConfigurationViewModel(getResources().getInteger(R.integer.min_text_size));
        binding.setViewModel(viewModel);

        backgroundColor = ContextCompat.getColor(MainActivity.this, R.color.default_memo_background_color);

        binding.selectBackgroundColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ChromaDialog.Builder()
                        .initialColor(ContextCompat.getColor(MainActivity.this, R.color.default_memo_background_color))
                        .colorMode(ColorMode.ARGB)
                        .onColorSelected(new OnColorSelectedListener() {
                            @Override
                            public void onColorSelected(@ColorInt int color) {
                                backgroundColor = color;
                                binding.backgroundColor.setBackgroundColor(backgroundColor);
                            }
                        })
                        .create()
                        .show(getSupportFragmentManager(), ChromaDialog.class.getName());
            }
        });

        binding.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(MainActivity.this);

                MemoAppWidget.updateAppWidget(MainActivity.this, appWidgetManager, appWidgetId,
                        viewModel.memo.get(), backgroundColor, viewModel.textSize.get());

                Intent i = new Intent();
                i.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
                setResult(RESULT_OK, i);

                finish();
            }
        });
    }
}