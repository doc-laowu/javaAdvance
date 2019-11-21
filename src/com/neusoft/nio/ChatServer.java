package com.neusoft.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * 使用NIO实现的群聊服务器端
 */
public class ChatServer {

    public static void main(String[] args) throws IOException {

        // 打开选择器实现多路复用
        Selector selector = Selector.open();
        // 针对面向流的侦听套接字的可选择通道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 实现 IP 套接字地址（IP 地址 + 端口号）
        serverSocketChannel.bind(new InetSocketAddress(8080));
        // 配置为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 将套接字接受连接的时间注册在选择器上面
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        System.out.println("start listen on the port 8080...");

        while (true){

            // 阻塞在选择器上面
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            // 遍历selectionKeys
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while(iterator.hasNext()){
                SelectionKey selectionKey = iterator.next();
                // 判断是不是accept事件
                if(selectionKey.isAcceptable()){
                    ServerSocketChannel ssc = (ServerSocketChannel)selectionKey.channel();
                    // 接受客户端连接
                    SocketChannel socketChannel = ssc.accept();
                    System.out.println(socketChannel.getRemoteAddress()+":connected...");
                    // 配置为非阻塞channal
                    socketChannel.configureBlocking(false);
                    // 将读取事件注册在选择器上面
                    socketChannel.register(selector, SelectionKey.OP_READ);

                    // 加入群聊
                    ChatHolder.join(socketChannel);

                }else if(selectionKey.isReadable()){

                    // 如果时读取事件
                    SocketChannel socketChannel = (SocketChannel)selectionKey.channel();
                    ByteBuffer buffer = ByteBuffer.allocate(1024);
                    // 将数据写入buffer中
                    int length = socketChannel.read(buffer);
                    if(length > 0){
                        buffer.flip();
                        byte[] bytes = new byte[buffer.remaining()];
                        //将数据读取到字节数组中
                        buffer.get(bytes);
                        // 换行符会跟着发送过来
                        String content = new String(bytes, "UTF-8")
                                .replaceAll("\r\n", "");

                        if(content.equalsIgnoreCase("quit")){
                            //退出群聊
                            ChatHolder.quit(socketChannel);
                            selectionKey.cancel();
                            System.out.println(socketChannel.getRemoteAddress()+":quit...");
                            socketChannel.close();
                        }else{
                            // 广播消息
                            ChatHolder.propagate(socketChannel, content);
                        }
                    }
                }
                // 将事件销毁
                iterator.remove();
            }

        }

    }

    /**
     * 业务处理逻辑类
     */
    private static class ChatHolder{

        // 创建用来存储用户连接上的套接字
        static final Map<SocketChannel, String> USER_MAP = new HashMap<>();

        /**
         * 加入群聊
         */
        static void join(SocketChannel socketChannel){

            // 当有人连接上的时候分配一个用户id
            String userId = "用户" + ThreadLocalRandom.current().nextInt(Integer.MAX_VALUE);
            send(socketChannel, "您的id为:"+userId);
            for(SocketChannel sc : USER_MAP.keySet()){
                send(sc, "[" + userId + "]:" + "加入了群聊\n\r");
            }

            // 将当前用户加入到Map中
            USER_MAP.put(socketChannel, userId);

        }

        /**
         * 退出群聊
         * @param socketChannel
         */
        static void quit(SocketChannel socketChannel){

            String user_id = USER_MAP.get(socketChannel);
            send(socketChannel, "您退出了群聊\n\r");

            USER_MAP.remove(socketChannel);
            for(SocketChannel sc : USER_MAP.keySet()){
                if(sc != socketChannel) {
                    send(sc, "[" + user_id + "]:" + "退出了群聊\n\r");
                }
            }

        }

        /**
         * 发送消息
         * @param socketChannel
         * @param msg
         */
        static void send(SocketChannel socketChannel, String msg){

            try {
                ByteBuffer buffer = ByteBuffer.allocate(1024);
                buffer.put(msg.getBytes());
                buffer.flip();
                socketChannel.write(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        /**
         *  广播内容
         * @param socketChannel
         * @param content
         */
        public static void propagate(SocketChannel socketChannel, String content){

            String user_id = USER_MAP.get(socketChannel);

            for(SocketChannel sc : USER_MAP.keySet()){
                if(sc != socketChannel) {
                    send(sc, "[" + user_id + "]:" + content + "\n\r");
                }
            }

        }

    }

}
