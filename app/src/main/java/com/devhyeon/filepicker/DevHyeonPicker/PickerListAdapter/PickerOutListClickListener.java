package com.devhyeon.filepicker.DevHyeonPicker.PickerListAdapter;

import android.view.View;

import com.devhyeon.filepicker.DevHyeonPicker.Model.PickerOutModel;

/**
 * Created by DevHyeon on 2020.11.24
 * */
public interface PickerOutListClickListener {
    void onItemClick(View view, PickerOutModel item, int position);
}
