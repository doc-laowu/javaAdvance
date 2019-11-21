package com.neusoft.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 基于Aio实现的EchoServer
 */
public class EchoServerByAio {

    public static void main(String[] args) throws IOException {

        AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open();

        serverSocketChannel.bind(new InetSocketAddress(8080));

        System.out.println("start listen on the port 8080...");

        // 监听Accept事件
        serverSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Object>(){

            @Override
            public void completed(AsynchronousSocketChannel socketChannel, Object attachment) {
                try {
                    System.out.println(socketChannel.getRemoteAddress()+":connected");
                    // 再次监听Accept事件
                    serverSocketChannel.accept(null, this);

                    // 消息处理
                    while(true){

                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        Future<Integer> future = socketChannel.read(buffer);
                        if(future.get() > 0){
                            buffer.flip();
                            byte[] bytes = new byte[buffer.remaining()];
                            buffer.get(bytes);

                            String content = new String(bytes, "UTF-8");
                            // 换行符会当作另外一条消息传过来
                            if("\r\n".equals(content)){
                                continue;
                            }

                            if(content.equalsIgnoreCase("quit")){
                                socketChannel.close();
                                break;
                            }else {
                                System.out.println("recv:"+content);
                            }

                        }

                    }


                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void failed(Throwable exc, Object attachment) {
                // 当出现错误的时候
                System.out.println("Occur error:"+exc.getMessage());
            }
        });

        // 阻塞住主线程
        System.in.read();

    }

}
