package com.qiqi.model.VO;

import lombok.Data;

@Data
public class UserVO {

    private long userId;
    private int age;
    private String name;

    public UserVO(){

    }

    public UserVO(long userId, int age, String name) {
        this.userId = userId;
        this.age = age;
        this.name = name;
    }
}
