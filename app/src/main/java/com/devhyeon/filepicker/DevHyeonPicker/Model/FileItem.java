package com.devhyeon.filepicker.DevHyeonPicker.Model;

import android.net.Uri;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by DevHyeon on 2020.11.24
 * */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FileItem {
    private String fileName;        //
    private String fileMimeType;    //
    private Uri    fileUri;         //
}