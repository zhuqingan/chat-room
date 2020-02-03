package com.tianjs.chatroom.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Author:zhuqa
 */
@Data
@Component
@ConfigurationProperties(prefix = "netty")
public class NettyAccountConfig {

    private int port;

    private int bossThread;

    private int workerThread;

    private boolean keepalive;

    private int backlog;

}
