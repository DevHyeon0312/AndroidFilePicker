package com.devhyeon.filepicker.DevHyeonPicker.Model;

import android.net.Uri;

import java.util.ArrayList;

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
public class Paths {
    private ArrayList<Uri> photoPaths       = new ArrayList<>();
    private ArrayList<Uri> docPaths         = new ArrayList<>();
    private ArrayList<Uri> allPaths         = new ArrayList<>();
    private ArrayList<FileItem> fileItems   = new ArrayList<>();
}

