package com.example.module.model;

import java.util.Objects;

public class CustomModuleData {
    private long id;
    private String name;

    public CustomModuleData(){}

    public CustomModuleData(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomModuleData)) return false;
        CustomModuleData co = (CustomModuleData) o;
        return id == co.id && Objects.equals(name, co.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "CustomModuleData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
