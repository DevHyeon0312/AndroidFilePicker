package com.devhyeon.filepicker.DevHyeonPicker.PickerListAdapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.devhyeon.filepicker.DevHyeonPicker.Model.FileItem;
import com.devhyeon.filepicker.DevHyeonPicker.Model.PickerOutModel;
import com.devhyeon.filepicker.DevHyeonPicker.PickerListViewHolder.PickerOutListViewHolder;
import com.devhyeon.filepicker.R;

import java.util.ArrayList;
import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by DevHyeon on 2020.11.24
 * List Orientation : Vertical
 * */
public class PickerOutListAdapter extends RecyclerView.Adapter<PickerOutListViewHolder> implements PickerOutListClickListener , PickerInListClickListener {
    private Activity mActivity;
    private Context mContext;
    private boolean isClickable;
    private ArrayList<PickerOutModel> pickerOutModelArrayList = new ArrayList<>();

    @Getter
    private HashMap<Integer, PickerInListAdapter> pickerInListAdapterHashMap = new HashMap<>();

    @Getter @Setter
    private int LastSelectIndex = -1;

    private PickerOutListClickListener pickerOutListClickListener;
    private PickerInListClickListener pickerInListClickListener;

    public PickerOutListAdapter(@NonNull Activity activity, @NonNull PickerOutListClickListener pickerOutListClickListener , @NonNull PickerInListClickListener pickerInListClickListener) {
        this.mActivity      = activity;
        this.mContext       = (Context) activity;
        this.isClickable    = false;
        this.pickerOutListClickListener = pickerOutListClickListener;
        this.pickerInListClickListener = pickerInListClickListener;
    }

    @Override
    public void onItemClick(View view, PickerOutModel item, int position) {
        if (pickerOutListClickListener != null) {
            LastSelectIndex = position;
            pickerOutListClickListener.onItemClick(view, item, position);
        }
    }

    @Override
    public void onItemClick(FileItem fileItem, int position) {
        if (pickerInListClickListener != null) {
            pickerInListClickListener.onItemClick(fileItem, position);
        }
    }

    @NonNull
    @Override
    public PickerOutListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //get Inflater
        LayoutInflater          mInflater   = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //get View
        View                    mView       = mInflater.inflate(R.layout.picker_out_list_item, parent, false);
        //create Holder
        PickerOutListViewHolder mViewHolder = new PickerOutListViewHolder(mView);

        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PickerOutListViewHolder holder, int position) {
        if (pickerOutModelArrayList == null || pickerOutModelArrayList.size() == 0) {
            return;
        }

        /** InItem */
        holder.itemXml.inRecyclerview.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        pickerInListAdapterHashMap.put(position, new PickerInListAdapter(mActivity, pickerOutModelArrayList.get(position).getPaths().getFileItems(), pickerInListClickListener));
        holder.itemXml.inRecyclerview.setAdapter(pickerInListAdapterHashMap.get(position));
        holder.itemXml.inRecyclerview.setItemAnimator(new DefaultItemAnimator());

        /** OutItem */
        PickerOutModel item = pickerOutModelArrayList.get(position);
        if (item != null) {
            holder.itemXml.textView.setText(item.getTitle());
            holder.itemXml.btnAdd.setOnClickListener(v -> onItemClick(v, item, position));
        }
    }

    @Override
    public int getItemCount() {
        return pickerOutModelArrayList.size();
    }

    public void addItem(PickerOutModel item) {
        pickerOutModelArrayList.add(item);
    }

    public PickerOutModel getOutModel(int pos) {
        return pickerOutModelArrayList.get(pos);
    }

}
