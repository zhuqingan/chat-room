package com.tianjs.chat_room.service;

import com.tianjs.chat_room.entity.User;

public interface CustService {
    User selectByWXparams(String openId,String sessionKey);

    int saveUserInfo(User user);
}
