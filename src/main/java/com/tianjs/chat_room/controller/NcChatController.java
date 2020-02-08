package com.tianjs.chat_room.controller;


import com.tianjs.chat_room.entity.User;
import com.tianjs.chat_room.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Author:zhuqa
 */
@Controller
@RequestMapping("/chat")
public class NcChatController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/netty")
    public ModelAndView netty(Map<String,Object> map){
        return new ModelAndView("h5",map);
    }

    /*@GetMapping("/user")
    @ResponseBody
    public User query(@RequestParam("level") Integer level){
        User user = userMapper.selectByLevel(level);
        return user;
    }*/
}
