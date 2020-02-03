package com.tianjs.chatroom.enums;

import lombok.Getter;

/**
 * @Author:zhuqa
 */
@Getter
public enum LevelStatusEnum {
    HIGH_LEVEL(1,"[高级别]"),
    MEDIUM_LEVEL(2,"[中级别]"),
    LOW_LEVEL(3,"[低级别]"),
    ;
    private Integer code;

    private String msg;

    LevelStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
