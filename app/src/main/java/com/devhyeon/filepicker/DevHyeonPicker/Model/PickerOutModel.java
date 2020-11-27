package com.devhyeon.filepicker.DevHyeonPicker.Model;


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
public class PickerOutModel {
    private String title;    //
    private Paths  paths;    //
}
