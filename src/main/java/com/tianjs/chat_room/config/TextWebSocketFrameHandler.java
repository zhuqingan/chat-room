package com.tianjs.chat_room.config;

import com.google.gson.Gson;
import com.tianjs.chat_room.constant.GradeLevelTemplate;
import com.tianjs.chat_room.entity.User;
import com.tianjs.chat_room.mapper.UserMapper;
import com.tianjs.chat_room.utils.CheckLevelUtils;
import com.tianjs.chat_room.vo.MsgVo;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Set;


/**
 * @Author:zhuqa
 */
@Component
@Qualifier("textWebSocketFrameHandler")
@ChannelHandler.Sharable
public class TextWebSocketFrameHandler extends SimpleChannelInboundHandler<TextWebSocketFrame>{

    public static ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    //圈子模式
    /*@Autowired
    private CircleLevelTemplate levelTemplate;*/
//    级别模式
    @Autowired
    private GradeLevelTemplate levelTemplate;

    @Autowired
    private UserMapper userMapper;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx,
                                TextWebSocketFrame msg) throws Exception {
        Channel incoming = ctx.channel();
        String text = msg.text();
        System.out.println(text);
        Gson gson = new Gson();
        MsgVo msgVo = gson.fromJson(text, MsgVo.class);
        //int inlevel =  Integer.parseInt(msg.text().substring(0,1));
        String realMsg =  text;
        Set<Channel> levelSet = null;
        //管理员通道
        if (msgVo!=null && msgVo.getLevel()==1){
            levelTemplate.save(msgVo.getLevel(),incoming);
            levelSet = levelTemplate.get(msgVo.getLevel());
        }
        //个人通道
        if (msgVo.getLevel() != 1) {
            levelSet = levelTemplate.get(1);
            levelSet.add(incoming);
        }
        //异步处理 级别归类存储 待定，异步存在数据慢消费（不做异步处理）
        //levelTemplate.save(msgVo.getLelvel(),incoming);
        //Set<Channel> levelSet = levelTemplate.get(msgVo.getLelvel());
        Iterator<Channel> iter = levelSet.iterator();
        //向所有连接推送消息
        while (iter.hasNext()){
            Channel channel = iter.next();
            channel.writeAndFlush(new TextWebSocketFrame(CheckLevelUtils.getLevelName(msgVo.getLevel())+realMsg));
        }
        channels.add(incoming);
    }


    /**
     * 连接加入，不区分权限高中低
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress());
        channels.add(ctx.channel());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        levelTemplate.remove(ctx.channel());
        channels.remove(ctx.channel());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("用户:在线");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("用户:掉线");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
            throws Exception {
        System.out.println("用户:异常");
        cause.printStackTrace();
        ctx.close();
    }
}
