package com.devhyeon.filepicker.DevHyeonPicker.PickerListViewHolder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devhyeon.filepicker.databinding.PickerInListItemBinding;

/**
 * Created by DevHyeon on 2020.11.24
 * */
public class PickerInListViewHolder extends RecyclerView.ViewHolder {
    public PickerInListItemBinding itemXml;

    public PickerInListViewHolder(@NonNull View itemView) {
        super(itemView);
        itemXml = PickerInListItemBinding.bind(itemView);
    }
}