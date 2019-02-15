package com.sam.pose.netty.handler;

import com.sam.pose.util.Global;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.websocketx.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
@ChannelHandler.Sharable
public class WebSocketHandler extends SimpleChannelInboundHandler<Object> {

    private static Logger logger = LoggerFactory.getLogger(WebSocketHandler.class);
    private WebSocketServerHandshaker handshaker;
    /**
     * channel 通道 action 活跃的 当客户端主动链接服务端的链接后，这个通道就是活跃的了。也就是客户端与服务端建立了通信通道并且可以传输数据
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端与服务端连接开启：" + ctx.channel().remoteAddress().toString());
    }
    /**
     * channel 通道 Inactive 不活跃的 当客户端主动断开服务端的链接后，这个通道就是不活跃的。也就是说客户端与服务端关闭了通信通道并且不可以传输数据
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 移除
        Global.group.remove(ctx.channel());
        String userId = null;
        /*if (ctx.attr(Global.CHANNEL_TOKEN_KEY).get() != null) {
            userId = ctx.attr(Global.CHANNEL_TOKEN_KEY).get().toString();
            if (Global.users.get(userId) != null) {
                Global.users.get(userId).remove(ctx.channel());
                RouteDTO routeDTO = new RouteDTO();
                routeDTO.setUserId(userId);
                routeDTO.setIp(getHost());
                routeDTO.setPort(8080);
                routeDTO.setUpdatedAt(new Date());
                routeDTO.setCreatedAt(new Date());
                if (!Global.users.get(userId).isEmpty())
                    this.routeService.delete(routeDTO);
            }
        }*/
        System.out.println("客户端与服务端连接关闭：" + ctx.channel().remoteAddress().toString());
    }
    /**
     * 接收客户端发送的消息 channel 通道 Read 读 简而言之就是从通道中读取数据，也就是服务端接收客户端发来的数据。但是这个数据在不进行解码时它是ByteBuf类型的
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("msg >>>>>>>>>>>>>>>>>> {} ",msg);
        System.out.println("msg >>>>>>>>>>>>>>>>>> " + msg.toString());
        // 传统的HTTP接入
        if (msg instanceof FullHttpRequest) {
            handleHttpRequest(ctx, ((FullHttpRequest) msg));
        // WebSocket接入
        } else if (msg instanceof WebSocketFrame) {
            System.out.println(handshaker.uri());
            handlerWebSocketFrame(ctx, (WebSocketFrame) msg);
        }
    }
    /**
     * channel 通道 Read 读取 Complete 完成 在通道读取完成后会在这个方法里通知，对应可以做刷新操作 ctx.flush()
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
    private void handlerWebSocketFrame(ChannelHandlerContext ctx, WebSocketFrame frame) {
        // 判断是否关闭链路的指令
        if (frame instanceof CloseWebSocketFrame) {
            System.out.println(1);
            handshaker.close(ctx.channel(), (CloseWebSocketFrame) frame.retain());
            return;
        }
        // 判断是否ping消息
        if (frame instanceof PingWebSocketFrame) {
            ctx.channel().write(new PongWebSocketFrame(frame.content().retain()));
            return;
        }
        // 本例程持二进制消息仅支持文本消息，不支
        if (!(frame instanceof TextWebSocketFrame)) {
            throw new UnsupportedOperationException(String.format("%s frame types not supported", frame.getClass().getName()));
        }
        // 返回应答消息
        String request = ((TextWebSocketFrame) frame).text();
        System.out.println("服务端收到：" + request);
        logger.info(String.format("%s received %s", ctx.channel(), request));
        String userId = null;
        if (ctx.attr(AttributeKey.valueOf("userId")).get() != null) {
            userId = ctx.attr(Global.CHANNEL_TOKEN_KEY).get().toString();
        }
    }

    private void handleHttpRequest(ChannelHandlerContext ctx, FullHttpRequest req) {
        //如果HTTP解码失败，返回HHTP异常
        if (!req.getDecoderResult().isSuccess() || (!"websocket".equals(req.headers().get("Upgrade")))) {
            sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.BAD_REQUEST));
            return;
        }
        //获取url后置参数
        HttpMethod method= req.getMethod();
        String uri= req.getUri();
        System.out.println("req.getUri() >>>>>>>>>>>>>>>>>>>>>>>> " + uri);
        String userId = null;
        if(method == HttpMethod.GET){
            //....处理
            userId = uri.substring(uri.lastIndexOf("/"));
            if (userId == null || "".equals(userId)) {
                logger.error("websocket uri not userId");
                sendHttpResponse(ctx, req, new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.FOUND));
                return;
            }else {
                userId = userId.substring(1);
                ctx.attr(Global.CHANNEL_TOKEN_KEY).set(userId);
            }

        }
        String wsURL = "ws://"+ req.headers().get(HttpHeaders.Names.HOST) + uri;
        System.out.println("wsURL >>>>>>>>>>>>>>>>>>>>>>>> " + wsURL);
        logger.info("wsURL >>>>>>>>>>>>>>> {} ",wsURL);
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(wsURL, null, false);
        handshaker = wsFactory.newHandshaker(req);
        ChannelGroup userGroup = null;
        if (Global.users.get(userId) == null) {
            userGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
        }else {
            userGroup = Global.users.get(userId);
        }
        userGroup.add(ctx.channel());
        userId="chenhaiquan";
        Global.users.put(userId,userGroup);
        if (handshaker == null) {
            WebSocketServerHandshakerFactory.sendUnsupportedWebSocketVersionResponse(ctx.channel());
        } else {
            handshaker.handshake(ctx.channel(), req);
        }
    }
    private static void sendHttpResponse(ChannelHandlerContext ctx, FullHttpRequest req, DefaultFullHttpResponse res) {
        // 返回应答给客户端
        if (res.getStatus().code() != 200) {
            ByteBuf buf = Unpooled.copiedBuffer(res.getStatus().toString(), CharsetUtil.UTF_8);
            res.content().writeBytes(buf);
            buf.release();
        }
        // 如果是非Keep-Alive，关闭连接
        ChannelFuture f = ctx.channel().writeAndFlush(res);
        if (!HttpHeaders.isKeepAlive(req) || res.getStatus().code() != 200) {
            f.addListener(ChannelFutureListener.CLOSE);
        }
    }
    /**
     * exception 异常 Caught 抓住 抓住异常，当发生异常的时候，可以做一些相应的处理，比如打印日志、关闭链接
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    /*
     * 功能：读空闲时移除Channel
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent evnet = (IdleStateEvent) evt;
            // 判断Channel是否读空闲, 读空闲时移除Channel
            if (evnet.state().equals(IdleState.READER_IDLE)) {
                //删除会话
                Global.group.remove(ctx.channel());
                String userId = null;
                if (ctx.attr(AttributeKey.valueOf("userId")).get() != null) {
                    userId = ctx.attr(Global.CHANNEL_TOKEN_KEY).get().toString();
                    if (Global.users.get(userId) != null) {
                        Global.users.get(userId).remove(ctx.channel());
                    }

                }
            }
        }
        ctx.fireUserEventTriggered(evt);
    }
    private String getHost(){
        try {
            InetAddress address = InetAddress.getLocalHost();
            String hostAddress = address.getHostAddress();
            return hostAddress;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return "";
    }
}