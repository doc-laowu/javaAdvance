package com.neusoft.ThreadPool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 自定义线程池测试类
 */
public class MyThreadPoolExecutorTest {

    public static void main(String[] args) {

        Executor threadPool = new MyThreadPoolExecutor("test", 5, 10,
                new ArrayBlockingQueue<>(15), new DiscardRejectPolicy());
        AtomicInteger num = new AtomicInteger(0);

        for(int i=0; i<100; i++){
            threadPool.execute(()->{
                try{

                    Thread.sleep(1000L);
                    System.out.println("running: " + System.currentTimeMillis() + ":" + num.incrementAndGet());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

    }

}
