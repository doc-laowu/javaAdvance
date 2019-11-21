package com.neusoft.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;


/**
 * 基于NIO实现的EchoServer
 */
public class EchoServerByNio {

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
                            selectionKey.cancel();
                            socketChannel.close();
                        }else{
                            System.out.println("recv:"+content);
                        }

                    }
                }
                // 将事件销毁
                iterator.remove();
            }

        }

    }

}
