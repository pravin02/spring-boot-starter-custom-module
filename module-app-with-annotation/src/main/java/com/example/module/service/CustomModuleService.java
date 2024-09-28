package com.example.module.service;

import com.example.module.model.CustomModuleData;

import java.util.Arrays;
import java.util.List;

public class CustomModuleService {

    public List<CustomModuleData> getCustomModuleData() {
        return Arrays.asList(
                new CustomModuleData(1l, "Pravin"),
                new CustomModuleData(2l, "Apoorva")
        );
    }
}
