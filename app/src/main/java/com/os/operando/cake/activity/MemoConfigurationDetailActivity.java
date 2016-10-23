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

import com.os.operando.cake.CakeApplication;
import com.os.operando.cake.R;
import com.os.operando.cake.appwidget.MemoAppWidget;
import com.os.operando.cake.databinding.ActivityMemoConfigurationDetailBinding;
import com.os.operando.cake.model.Memo;
import com.os.operando.cake.viewmodel.MemoConfigurationViewModel;
import com.pavelsikun.vintagechroma.ChromaDialog;
import com.pavelsikun.vintagechroma.OnColorSelectedListener;
import com.pavelsikun.vintagechroma.colormode.ColorMode;

public class MemoConfigurationDetailActivity extends AppCompatActivity {

    private ActivityMemoConfigurationDetailBinding binding;
    private MemoConfigurationViewModel viewModel;

    private int appWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;

    public static Intent createIntent(Context context) {
        Intent i = new Intent(context, MemoConfigurationDetailActivity.class);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setResult(RESULT_CANCELED);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_memo_configuration_detail);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            appWidgetId = bundle.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        if (appWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID) {
            finish();
        }

        viewModel = new MemoConfigurationViewModel(getResources().getInteger(R.integer.min_text_size));
        viewModel.backgroundColor.set(ContextCompat.getColor(MemoConfigurationDetailActivity.this, R.color.default_memo_background_color));
        viewModel.textColor.set(ContextCompat.getColor(this, R.color.default_memo_text_color));
        binding.setViewModel(viewModel);

        binding.backgroundColor.setOnClickListener(onBackgroundColorChangeClickListener);
        binding.selectBackgroundColor.setOnClickListener(onBackgroundColorChangeClickListener);

        binding.textColor.setOnClickListener(onTextColorChangeClickListener);
        binding.selectTextColor.setOnClickListener(onTextColorChangeClickListener);

        binding.ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(MemoConfigurationDetailActivity.this);

                MemoAppWidget.updateAppWidget(MemoConfigurationDetailActivity.this, appWidgetManager, appWidgetId,
                        viewModel.memo.get(), viewModel.backgroundColor.get(), viewModel.textSize.get(), viewModel.textColor.get());

                Memo memo = new Memo();
                memo.nemo = viewModel.memo.get();
                memo.backgroundColor = viewModel.backgroundColor.get();
                memo.textSize = viewModel.textSize.get();
                memo.textColor = viewModel.textColor.get();

                CakeApplication.getOrma().insertIntoMemo(memo);

                Intent i = new Intent();
                i.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
                setResult(RESULT_OK, i);

                finish();
            }
        });
    }

    private View.OnClickListener onBackgroundColorChangeClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            new ChromaDialog.Builder()
                    .initialColor(viewModel.backgroundColor.get())
                    .colorMode(ColorMode.ARGB)
                    .onColorSelected(new OnColorSelectedListener() {
                        @Override
                        public void onColorSelected(@ColorInt int color) {
                            viewModel.backgroundColor.set(color);
                        }
                    })
                    .create()
                    .show(getSupportFragmentManager(), ChromaDialog.class.getName());
        }
    };

    private View.OnClickListener onTextColorChangeClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            new ChromaDialog.Builder()
                    .initialColor(viewModel.textColor.get())
                    .colorMode(ColorMode.ARGB)
                    .onColorSelected(new OnColorSelectedListener() {
                        @Override
                        public void onColorSelected(@ColorInt int color) {
                            viewModel.textColor.set(color);
                        }
                    })
                    .create()
                    .show(getSupportFragmentManager(), ChromaDialog.class.getName());
        }
    };
}
