package com.kinger.iohackathon.httpServerHandler;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.ds.simple.SimpleDataSource;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kinger.iohackathon.common.OperateResult;
import com.kinger.iohackathon.operate.DataModel;
import com.kinger.iohackathon.operate.OperateCommonUtils;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;

import javax.sql.DataSource;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * HTTP 服务器处理类
 * SimpleChannelInboundHandler 是 SimpleChannelInboundHandler 子类
 * FullHttpRequest 指的是服务器端与客户端处理数据时的数据类型
 */
public class HTTPServerHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        System.out.println("Connected!");
    }

    public static AtomicLong count = new AtomicLong(0);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest msg) throws Exception {
        JSONObject obj = new JSONObject();
        String [] ipArray = ctx.channel().remoteAddress().toString().split(":");
        obj.put("ip", ipArray[0]);
        obj.put("port", ipArray[1]);
        obj.put("count",count.incrementAndGet());
        System.out.println("metadata" + ctx.channel().metadata());
        obj.put("create_time", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        System.out.println(msg.toString());

        if (msg instanceof FullHttpRequest) { //判断该  HttpObject msg 参数是否是 Http 请求
            FullHttpRequest http = (FullHttpRequest) msg;

            System.out.println("Recieved request!");
            System.out.println("HTTP Method: " + msg.getMethod());
            System.out.println("HTTP Version: " + msg.getProtocolVersion());
            System.out.println("URI: " + msg.getUri());
            System.out.println("Headers: " + msg.headers());
            System.out.println("Trailing headers: " + msg.trailingHeaders());

            ByteBuf data = msg.content();
            System.out.println("POST/PUT length: " + data.readableBytes());
            System.out.println("POST/PUT as string: ");
            System.out.println("-- DATA --");
            DataModel dataModel = JSONObject.parseObject(data.toString(StandardCharsets.UTF_8), DataModel.class);

            System.out.println("dataHashMap" + dataModel.toString());
            System.out.println();
            System.out.println("-- DATA END --");
            System.out.println("url====>>" + http.uri());
            System.out.println("mothod====>>" + http.method().toString());
            System.out.println(ctx.channel().remoteAddress() + " 客户端请求数据 ... ");
            ByteBuf byteBuf = null;
            if (msg.getUri().endsWith("/set")) {
                boolean flag = OperateCommonUtils.set(new String(dataModel.getKey(), "UTF-8"), dataModel.getValue());
                byteBuf = Unpooled.copiedBuffer(JSON.toJSONBytes(OperateResult.ok(flag)));
            } else if (msg.getUri().endsWith("/get")) {
                byte[] value = OperateCommonUtils.get(new String(dataModel.getKey(), "UTF-8"));
                byteBuf = Unpooled.copiedBuffer(JSON.toJSONBytes(OperateResult.ok(value == null ? Arrays.toString("null".getBytes("UTF-8")) : Arrays.toString(value))));
            } else if (msg.getUri().endsWith("/del")) {
                byte[] value = OperateCommonUtils.del(new String(dataModel.getKey(), "UTF-8"));
                byteBuf = Unpooled.copiedBuffer(JSON.toJSONBytes(OperateResult.ok(value == null ? Arrays.toString("null".getBytes("UTF-8")) : Arrays.toString(value))));
            } else if (msg.getUri().endsWith("/hasKey")) {
                boolean flag = OperateCommonUtils.hasKey(new String(dataModel.getKey(), "UTF-8"));
                byteBuf = Unpooled.copiedBuffer(JSON.toJSONBytes(OperateResult.ok(Arrays.toString(String.valueOf(flag).getBytes("UTF-8")))));
            }

            // 准备给客户端浏览器发送的数据
            // 设置 HTTP 版本, 和 HTTP 的状态码, 返回内容
            DefaultFullHttpResponse defaultFullHttpResponse =
                    new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                            HttpResponseStatus.OK, byteBuf);

            // 设置 HTTP 请求头
            // 设置内容类型是文本类型
            defaultFullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            // 设置返回内容的长度
            defaultFullHttpResponse.headers().set(
                    HttpHeaderNames.CONTENT_LENGTH,
                    byteBuf.readableBytes());

            // 写出 HTTP 数据
            ctx.writeAndFlush(defaultFullHttpResponse);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        insert(obj, "netty");
                    } catch (Exception e) {
                    }
                }
            }).start();
        } else {
            insert(obj, "netty_http");
        }

    }

    public void  insert(JSONObject obj, String table) throws SQLException {
        System.out.println("insert=====>>>>");
        List<Entity> list = new ArrayList<>();
        Entity entity = Entity.create(table).addFieldNames("ip", "port","count", "create_time");
        list.add(entity);
        for (Map.Entry<String, Object> entry : obj.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
            entity.set(entry.getKey(), entry.getValue());
        }
        DataSource dataSource = new SimpleDataSource("jdbc:mysql://192.168.230.129:3306/test?useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&autoReconnect=true&useSSL=false", "root",
                "Small@521", "com.mysql.jdbc.Driver");
        int[] insert = Db.use(dataSource).insert(list);
    }

}
