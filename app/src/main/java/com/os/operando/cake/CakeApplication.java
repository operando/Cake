package com.os.operando.cake;

import android.app.Application;

import com.github.gfx.android.orma.AccessThreadConstraint;
import com.os.operando.cake.model.OrmaDatabase;

public class CakeApplication extends Application {

    private static OrmaDatabase orma;

    @Override
    public void onCreate() {
        super.onCreate();

        orma = OrmaDatabase.builder(this)
                .trace(true)
                .writeOnMainThread(AccessThreadConstraint.NONE)
                .readOnMainThread(AccessThreadConstraint.NONE)
                .build();
    }

    public static OrmaDatabase getOrma() {
        return orma;
    }
}
