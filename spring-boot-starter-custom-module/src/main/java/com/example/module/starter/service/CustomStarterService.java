package com.example.module.starter.service;

import java.util.List;
import java.util.stream.IntStream;

public class CustomStarterService {

    public List<Integer> getData() {
        return IntStream.range(1, 101)
                .boxed()
                .toList();
    }
}
