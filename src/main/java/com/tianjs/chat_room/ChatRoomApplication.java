package com.tianjs.chat_room;

import com.tianjs.chat_room.config.NettyConfig;
import com.tianjs.chat_room.config.TCPServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ChatRoomApplication {

    public static void main(String[] args) throws Exception{
        ConfigurableApplicationContext context = SpringApplication.run(ChatRoomApplication.class, args);
        NettyConfig nettyConfig = context.getBean(NettyConfig.class);
        TCPServer tcpServer = context.getBean(TCPServer.class);
        tcpServer.start();
    }

}
