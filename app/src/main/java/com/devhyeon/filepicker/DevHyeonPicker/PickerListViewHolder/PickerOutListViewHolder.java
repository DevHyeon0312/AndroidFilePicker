package com.devhyeon.filepicker.DevHyeonPicker.PickerListViewHolder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devhyeon.filepicker.databinding.PickerOutListItemBinding;

/**
 * Created by DevHyeon on 2020.11.24
 * */
public class PickerOutListViewHolder extends RecyclerView.ViewHolder {
    public PickerOutListItemBinding itemXml;

    public PickerOutListViewHolder(@NonNull View itemView) {
        super(itemView);
        itemXml = PickerOutListItemBinding.bind(itemView);
    }
}