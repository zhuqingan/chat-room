package com.tianjs.chat_room.mapper;

import com.tianjs.chat_room.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface UserMapper {
    User selectByLevel(@Param("level") Integer level);
}
