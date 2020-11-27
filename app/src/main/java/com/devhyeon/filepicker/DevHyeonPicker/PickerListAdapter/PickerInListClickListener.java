package com.devhyeon.filepicker.DevHyeonPicker.PickerListAdapter;

import com.devhyeon.filepicker.DevHyeonPicker.Model.FileItem;

/**
 * Created by DevHyeon on 2020.11.24
 * */
public interface PickerInListClickListener {
    void onItemClick(FileItem fileItem, int position);
}
