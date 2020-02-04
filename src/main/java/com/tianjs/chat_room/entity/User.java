package com.tianjs.chat_room.entity;

/**
 * @Author:zhuqa
 */
public class User {
    private Integer id;

    private String openId;

    private String nickName;

    private String phone;

    private Integer levelId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getLevel() {
        return levelId;
    }

    public void setLevel(Integer level) {
        this.levelId = level;
    }
}
