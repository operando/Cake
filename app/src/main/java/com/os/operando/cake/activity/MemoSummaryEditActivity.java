package com.os.operando.cake.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.os.operando.cake.R;
import com.os.operando.cake.databinding.ActivityMemoSummaryEditBinding;

public class MemoSummaryEditActivity extends AppCompatActivity {

    private static final String EXTRA_MEMO_ID = "memo_id";

    private ActivityMemoSummaryEditBinding binding;

    public static Intent createIntent(Context context, long memoId) {
        Intent i = new Intent(context, MemoSummaryEditActivity.class);
        i.putExtra(EXTRA_MEMO_ID, memoId);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_memo_summary_edit);

//        long id = getIntent().getLongExtra(EXTRA_MEMO_ID, -1);
//
//        Memo memo = CakeApplication.getOrma().selectFromMemo().idEq(id).value();
//        binding.memo.setText(memo.nemo);
    }
}
