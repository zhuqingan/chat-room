package com.tianjs.chat_room.utils;

import com.tianjs.chat_room.enums.LevelStatusEnum;

/**
 * @Author:zhuqa
 */
public class CheckLevelUtils {

    public static String getLevelName(Integer code){

            switch (code){
                //高级别
                case 1:
                    return LevelStatusEnum.HIGH_LEVEL.getMsg();
                //中级别
                case 2:
                    return LevelStatusEnum.MEDIUM_LEVEL.getMsg();
                //低级别
                case 3:
                    return LevelStatusEnum.LOW_LEVEL.getMsg();
                default:
                    return null;
            }

    }

}
