package com.example.demo;

import com.example.module.annotation.EnableCustomModule;
import com.example.module.service.CustomModuleService;

import com.example.module.starter.service.CustomStarterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableCustomModule
@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    @Autowired
    CustomModuleService customModuleService;

    @Autowired
    CustomStarterService customStarterService;

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) {
        customModuleService.getCustomModuleData().forEach(System.out::println);

        System.out.println("------------ autoconfigurer -----------");
        customStarterService.getData().forEach(System.out::println);
    }
}
