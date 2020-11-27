package com.devhyeon.filepicker.DevHyeonPicker.PickerListAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devhyeon.filepicker.DevHyeonPicker.Model.FileItem;
import com.devhyeon.filepicker.DevHyeonPicker.PickerListViewHolder.PickerInListViewHolder;
import com.devhyeon.filepicker.R;

import java.util.ArrayList;

/**
 * Created by DevHyeon on 2020.11.24
 * List Orientation : Horizontal
 * */
public class PickerInListAdapter extends RecyclerView.Adapter<PickerInListViewHolder> implements PickerInListClickListener {
    private Activity mActivity;
    private Context mContext;
    private boolean isClickable;
    private ArrayList<FileItem> pickerInModelArrayList = new ArrayList<>();

    private PickerInListClickListener pickerInListClickListener;

    public PickerInListAdapter(@NonNull Activity activity, ArrayList<FileItem> pickerInModelArrayList, @NonNull PickerInListClickListener pickerInListClickListener) {
        this.mActivity = activity;
        this.mContext = (Context) activity;
        this.isClickable = false;
        this.pickerInModelArrayList = pickerInModelArrayList;
        this.pickerInListClickListener = pickerInListClickListener;
    }

    @Override
    public void onItemClick(FileItem item, int position) {
        if (pickerInListClickListener != null) {
            pickerInListClickListener.onItemClick(item, position);
        }
    }

    @NonNull
    @Override
    public PickerInListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //get Inflater
        LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //get View
        View mView = mInflater.inflate(R.layout.picker_in_list_item, parent, false);
        //create Holder
        PickerInListViewHolder mViewHolder = new PickerInListViewHolder(mView);

        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PickerInListViewHolder holder, int position) {
        if (pickerInModelArrayList == null || pickerInModelArrayList.size() == 0) {
            return;
        }

        FileItem item = pickerInModelArrayList.get(position);
        if (item != null) {
            holder.itemView.setOnClickListener(v -> onItemClick(item, position));

            holder.itemXml.text.setText(item.getFileName());
        }
    }

    @Override
    public int getItemCount() {
        return pickerInModelArrayList.size();
    }

    public void addItem(FileItem item) {
        pickerInModelArrayList.add(item);
    }

    public void addItems(ArrayList<FileItem> items) {
        pickerInModelArrayList.clear();
        pickerInModelArrayList.addAll(items);
    }
}