package com.devhyeon.filepicker.DevHyeonPicker.PickerListView;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by DevHyeon on 2020.11.24
 * List Orientation : Vertical
 * */
public class PickerOutListView extends RecyclerView {

    public PickerOutListView(@NonNull Context context) {
        super(context);
    }

    public PickerOutListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PickerOutListView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    /**
     * Created by DevHyeon on 2020.11.24
     * Func : Scroll Vertical vs Horizontal Motion Check.
     * */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        return super.onInterceptTouchEvent(e);
    }
}