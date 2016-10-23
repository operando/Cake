package com.os.operando.cake.model;

import com.github.gfx.android.orma.annotation.Column;
import com.github.gfx.android.orma.annotation.PrimaryKey;
import com.github.gfx.android.orma.annotation.Table;

@Table
public class Memo {

    @PrimaryKey(autoincrement = true)
    public long id;

    @Column
    public String nemo;

    @Column
    public float textSize;

    @Column
    public int textColor;

    @Column
    public int backgroundColor;
}