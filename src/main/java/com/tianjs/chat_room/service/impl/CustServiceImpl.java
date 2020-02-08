package com.tianjs.chat_room.service.impl;

import com.tianjs.chat_room.entity.User;
import com.tianjs.chat_room.mapper.UserMapper;
import com.tianjs.chat_room.service.CustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustServiceImpl implements CustService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User selectByWXparams(String openId, String sessionKey) {
        return userMapper.selectByWXparams(openId,sessionKey);
    }

    @Override
    public int saveUserInfo(User user) {
        return userMapper.insert(user);
    }
}
