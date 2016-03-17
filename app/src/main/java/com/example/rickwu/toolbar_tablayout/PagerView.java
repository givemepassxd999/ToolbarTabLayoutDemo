package com.example.rickwu.toolbar_tablayout;

import android.content.Context;
import android.widget.RelativeLayout;

public abstract class PagerView extends RelativeLayout {
    public PagerView(Context context) {
        super(context);
    }

    public abstract void refreshView();
}