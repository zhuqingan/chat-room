package com.tianjs.chat_room.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import com.tianjs.chat_room.entity.User;
import com.tianjs.chat_room.service.CustService;
import com.tianjs.chat_room.utils.RedisUtil;
import com.tianjs.chat_room.utils.SystemConstant;
import com.tianjs.chat_room.vo.ResponseResult;
import me.chanjar.weixin.common.error.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cust")
public class CustController {

    Logger logger = LoggerFactory.getLogger(CustController.class);

    @Autowired
    private CustService custService;

    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/login")
    public ResponseResult login(@RequestParam("code") String code, @RequestParam("nickName")String nickName, @RequestParam("avatarUrl")String avatarUrl){
        WxMaService wxService = getWxService();
        try {
            WxMaJscode2SessionResult session = wxService.getUserService().getSessionInfo(code);
            String sessionKey = session.getSessionKey();
            String openId = session.getOpenid();
            User user = custService.selectByWXparams(openId,sessionKey);
            if (user == null) {
                user.setNickName(nickName);
                user.setAvatarUrl(avatarUrl);
                user.setOpenId(openId);
                user.setSessionKey(sessionKey);
                user.setLevelId(null);
                custService.saveUserInfo(user);
            }
            Map<String, String> map = new HashMap<>();
            map.put("openId",openId);
            map.put("sessionKey",sessionKey);
            return ResponseResult.ok(map);
        } catch (WxErrorException e) {
            logger.error("小程序获取openId异常", e);
            return ResponseResult.systemError();
        }
    }

    @RequestMapping("/test")
    public ResponseResult test(String username){
        redisUtil.set("name",username);
        String name = (String)redisUtil.get("name");
        System.out.println(name);
        return ResponseResult.ok(name);
    }

    /*@RequestMapping("/bindphone")
    public ResponseResult bindphone(String openId, String sessionKey, String encryptedData, String iv) throws Exception {
        WxMaService wxService = getWxService();
        // 解密
        WxMaPhoneNumberInfo phoneNoInfo = wxService.getUserService().getPhoneNoInfo(sessionKey, encryptedData, iv);
        String phone = phoneNoInfo.getPhoneNumber();
        Cust cust = custService.saveBindPhone(phone, openId, chanlCode);
        return getToken(cust);
    }*/

    private WxMaService getWxService() {
        WxMaDefaultConfigImpl config = new WxMaDefaultConfigImpl();
        config.setAppid(SystemConstant.MINIAPP_APPID);
        config.setSecret(SystemConstant.MINIAPP_SECRET);
        WxMaService wxService = new WxMaServiceImpl();
        wxService.setWxMaConfig(config);
        return wxService;
    }
}
