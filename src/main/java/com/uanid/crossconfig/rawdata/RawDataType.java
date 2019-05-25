package com.uanid.crossconfig.rawdata;

import com.uanid.crossconfig.type.Type;

import java.util.Objects;

public class RawDataType implements Type {

    private String name;
    private Class dataClass;

    public RawDataType(String name, Class dataClass) {
        this.name = name;
        this.dataClass = dataClass;
    }

    @Override
    public String getName() {
        return name;
    }

    public Class getDataClass() {
        return dataClass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof RawDataType) return false;
        RawDataType that = (RawDataType) o;
        return name.equals(that.name) && dataClass.equals(that.dataClass);
    }
}
