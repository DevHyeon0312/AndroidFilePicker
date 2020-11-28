package com.devhyeon.filepicker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.devhyeon.filepicker.Base.BaseActivity;
import com.devhyeon.filepicker.DevHyeonPicker.Define.CODE;
import com.devhyeon.filepicker.DevHyeonPicker.Model.FileItem;
import com.devhyeon.filepicker.DevHyeonPicker.Model.Paths;
import com.devhyeon.filepicker.DevHyeonPicker.Model.PickerOutModel;
import com.devhyeon.filepicker.DevHyeonPicker.PickerListAdapter.PickerInListClickListener;
import com.devhyeon.filepicker.DevHyeonPicker.PickerListAdapter.PickerOutListAdapter;
import com.devhyeon.filepicker.DevHyeonPicker.PickerListAdapter.PickerOutListClickListener;
import com.devhyeon.filepicker.databinding.ActivityMainBinding;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import droidninja.filepicker.models.sort.SortingTypes;
import lombok.ToString;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends BaseActivity<ActivityMainBinding> implements EasyPermissions.PermissionCallbacks {
    PickerOutListAdapter adapter;
    PickerOutListClickListener listClickListener;   //out Item Click
    PickerInListClickListener listClickListener2;   //in Item Click

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setBindingView(getView(R.layout.activity_main));

        listClickListener = (view, item, position) -> {
            SelectType(view, item, position);
        };
        listClickListener2 = new PickerInListClickListener() {
            @Override
            public void onItemClick(FileItem fileItem, int position) {
                Toast.makeText(mActivity, "FileItem Detail View", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDeleteClick(FileItem fileItem, int parentPosition, int childPosition) {
                Toast.makeText(mActivity,"FileItem Delete Click " + childPosition,Toast.LENGTH_SHORT).show();

                adapter.getOutModel(parentPosition).getPaths().getAllPaths().remove((Object)fileItem.getFileUri());
                adapter.getOutModel(parentPosition).getPaths().getPhotoPaths().remove((Object)fileItem.getFileUri());
                adapter.getOutModel(parentPosition).getPaths().getDocPaths().remove((Object)fileItem.getFileUri());
                adapter.getOutModel(parentPosition).getPaths().getFileItems().remove((Object)fileItem);
                adapter.notifyDataSetChanged();
            }

        };

        adapter = new PickerOutListAdapter(this, listClickListener, listClickListener2);

        xml.recyclerview.setLayoutManager(new LinearLayoutManager(mActivity));
        xml.recyclerview.setAdapter(adapter);


        adapter.addItem(
                PickerOutModel.builder()
                        .title("1. 강아지")
                        .paths(new Paths())
                        .build()
        );
        adapter.addItem(
                PickerOutModel.builder()
                        .title("2. 고양이")
                        .paths(new Paths())
                        .build()
        );
        adapter.addItem(
                PickerOutModel.builder()
                        .title("3. 토끼")
                        .paths(new Paths())
                        .build()
        );
        adapter.addItem(
                PickerOutModel.builder()
                        .title("4. 새")
                        .paths(new Paths())
                        .build()
        );
        adapter.addItem(
                PickerOutModel.builder()
                        .title("5. 하늘")
                        .paths(new Paths())
                        .build()
        );
        adapter.addItem(
                PickerOutModel.builder()
                        .title("6. 바다")
                        .paths(new Paths())
                        .build()
        );
        adapter.addItem(
                PickerOutModel.builder()
                        .title("7. 땅")
                        .paths(new Paths())
                        .build()
        );
        adapter.addItem(
                PickerOutModel.builder()
                        .title("8.우주")
                        .paths(new Paths())
                        .build()
        );
        adapter.addItem(
                PickerOutModel.builder()
                        .title("9.나무")
                        .paths(new Paths())
                        .build()
        );
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void setBindingView(@NonNull View view) {
        setContentsView(view,ActivityMainBinding.bind(view));
    }

    /**
     * popup ( Select Type. )
     * */
    private void SelectType(View view, PickerOutModel model, int position) {
        PopupMenu popupMenu = new PopupMenu(mContext, view);
        popupMenu.getMenuInflater().inflate(R.menu.file_menu, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(item -> {
            switch (item.getItemId()) {
                case R.id.menu_gallery:
                    PhotoClicked(view, model, position);
                    break;
                case R.id.menu_file:
                    DocClicked(view, model, position);
                    break;
            }
            return true;
        });
        popupMenu.show();
    }

    /**
     * Select Photo
     * */
    @AfterPermissionGranted(CODE.RC_PHOTO_PICKER_PERM)
    public void PhotoClicked(View view, PickerOutModel model, int position) {
        if (EasyPermissions.hasPermissions(mContext, FilePickerConst.PERMISSIONS_FILE_PICKER)) {
            startPickPhoto(view, model, position);
        } else {
            EasyPermissions.requestPermissions(this, "PERMISSION MESSAGE", CODE.RC_PHOTO_PICKER_PERM, FilePickerConst.PERMISSIONS_FILE_PICKER);
        }
    }
    private void startPickPhoto(View view, PickerOutModel model, int position) {
        int maxCount = CODE.MAX_SELECT_COUNT - model.getPaths().getAllPaths().size();
        if (maxCount <= 0) {
            Toast.makeText(mActivity, "더이상 선택할 수 없습니다.", Toast.LENGTH_SHORT).show();
        } else {
            //TODO : Select Photo

            Log.i("DevHyeon",model.getPaths().toString());

            FilePickerBuilder.getInstance()
                    .setMaxCount(maxCount)
                    .setSelectedFiles(model.getPaths().getPhotoPaths())
                    .setActivityTheme(R.style.FilePickerTheme)
                    .setActivityTitle("이미지를 선택하세요.")
                    .enableVideoPicker(false)
                    .enableCameraSupport(true)
                    .showGifs(true)
                    .showFolderView(true)
                    .enableSelectAll(false)
                    .enableImagePicker(true)
                    .setCameraPlaceholder(R.drawable.ic_camera)
                    .withOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                    .pickPhoto(this, CODE.FILE_SELECT_REQUEST_CODE);
        }
    }

    /**
     * Select Doc
     * */
    @AfterPermissionGranted(CODE.RC_FILE_PICKER_PERM)
    public void DocClicked(View view, PickerOutModel model, int position) {
        if (EasyPermissions.hasPermissions(mContext, FilePickerConst.PERMISSIONS_FILE_PICKER)) {
            startPickDoc(view, model, position);
        } else {
            EasyPermissions.requestPermissions(this, "PERMISSION MESSAGE", CODE.RC_FILE_PICKER_PERM, FilePickerConst.PERMISSIONS_FILE_PICKER);
        }
    }
    private void startPickDoc(View view, PickerOutModel model, int position) {
        int maxCount = CODE.MAX_SELECT_COUNT - model.getPaths().getAllPaths().size();
        if (maxCount <= 0) {
            Toast.makeText(mActivity, "더이상 선택할 수 없습니다.", Toast.LENGTH_SHORT).show();
        } else {
            FilePickerBuilder.getInstance()
                    .setMaxCount(maxCount)
                    .setSelectedFiles(model.getPaths().getDocPaths())
                    .setActivityTheme(R.style.FilePickerTheme)
                    .setActivityTitle("파일을 선택하세요.")
                    .enableDocSupport(true)
                    .enableSelectAll(true)
                    .sortDocumentsBy(SortingTypes.name)
                    .withOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
                    .pickFile(this);
        }
    }

    /**
     * Update List
     * */
    private void updateList(int pos, @NonNull ArrayList<Uri> list) {

        Log.i("DevHyeon List : ",list.toString());
        Log.i("DevHyeon Pos : ", pos+"");
        if (pos != -1) {
            adapter.getOutModel(pos).getPaths().getPhotoPaths().clear();
            adapter.getOutModel(pos).getPaths().setPhotoPaths(list);

            adapter.getOutModel(pos).getPaths().getAllPaths().clear();
            adapter.getOutModel(pos).getPaths().getAllPaths().addAll(adapter.getOutModel(pos).getPaths().getPhotoPaths());
            adapter.getOutModel(pos).getPaths().getAllPaths().addAll(adapter.getOutModel(pos).getPaths().getDocPaths());

            ArrayList<FileItem> newFile = new ArrayList<>();
            for (Uri uri : adapter.getOutModel(pos).getPaths().getAllPaths()) {
                newFile.add(new FileItem("item","",uri));
            }
            adapter.getPickerInListAdapterHashMap().get(pos).addItems(newFile);
            adapter.getPickerInListAdapterHashMap().get(pos).notifyDataSetChanged();
            adapter.notifyDataSetChanged();

            Log.i("DevHyeon",adapter.getOutModel(pos).getPaths().toString());
        }
    }

    /**
     * Result
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        switch (requestCode) {
            case CODE.FILE_SELECT_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    int pos = adapter.getLastSelectIndex();
                    ArrayList<Uri> photoList = data.getParcelableArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA);
                    if (photoList != null) {
                        updateList(pos, photoList);
                    }
                }
                break;
            case FilePickerConst.REQUEST_CODE_DOC:
                if (resultCode == Activity.RESULT_OK && data != null) {
                    int pos = adapter.getLastSelectIndex();
                    ArrayList<Uri> docList = data.getParcelableArrayListExtra(FilePickerConst.KEY_SELECTED_DOCS);
                    if (docList != null) {
                        updateList(pos, docList);
                    }
                }
                break;
        }
    }


    /**
     * Permissions
     * */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }
    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {}
    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            new AppSettingsDialog.Builder(mActivity).build().show();
        }
    }
}