package com.devhyeon.filepicker.DevHyeonPicker.PickerListAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
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
    private int parentIndex=-1;
    private ArrayList<FileItem> pickerInModelArrayList = new ArrayList<>();

    private PickerInListClickListener pickerInListClickListener;

    public PickerInListAdapter(int parentIndex, @NonNull Activity activity, ArrayList<FileItem> pickerInModelArrayList, @NonNull PickerInListClickListener pickerInListClickListener) {
        this.mActivity = activity;
        this.mContext = (Context) activity;
        this.isClickable = false;
        this.parentIndex = parentIndex;
        this.pickerInModelArrayList = pickerInModelArrayList;
        this.pickerInListClickListener = pickerInListClickListener;
    }


    @Override
    public void onItemClick(FileItem fileItem, int childPosition) {
        if (pickerInListClickListener != null) {
            pickerInListClickListener.onItemClick(fileItem, childPosition);
        }
    }

    @Override
    public void onDeleteClick(FileItem fileItem, int parentPosition, int childPosition) {
        if (pickerInListClickListener != null) {
            pickerInListClickListener.onDeleteClick(fileItem, parentIndex, childPosition);
            pickerInModelArrayList.remove(fileItem);
            notifyDataSetChanged();
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
            //Item Click Event
            holder.itemView.setOnClickListener(v -> onItemClick(item, position));
            //Item Delete Click Event
            holder.itemXml.ivDelete.setOnClickListener(v -> onDeleteClick(item, parentIndex, position));
            //Item Set Image
            Glide.with(mContext)
                    .load(item.getFileUri())
                    .apply(
                            RequestOptions.centerCropTransform().dontAnimate().placeholder(droidninja.filepicker.R.drawable.icon_file_unknown)
                    )
                    .thumbnail(0.5f)
                    .into(holder.itemXml.ivFile);
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