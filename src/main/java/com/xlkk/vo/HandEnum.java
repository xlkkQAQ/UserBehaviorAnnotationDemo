package com.xlkk.vo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public enum HandEnum {
    SAVE("SAVE","保存"),
    UPDATE("UPDATE","更新"),
    SELECT("SELECT","查询"),
    DELETE("DELETE","删除");

    private String name;
    private String desc;
    HandEnum(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
