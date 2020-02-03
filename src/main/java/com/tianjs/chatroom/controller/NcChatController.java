package com.tianjs.chatroom.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Author:zhuqa
 */
@Controller
@RequestMapping("/chat")
public class NcChatController {


    @GetMapping("/netty")
    public ModelAndView netty(Map<String,Object> map){
        return new ModelAndView("h5",map);
    }

}
