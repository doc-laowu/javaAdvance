package com.neusoft.ThreadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 带返回值的线程池测试类
 */
public class MyThreadPoolFutureExecutorTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureExecutor threadPool =
                new MyThreadPoolFutureExecutor("test", 2, 4, new ArrayBlockingQueue<>(6), new DiscardRejectPolicy());

        List<Future<Integer>> list = new ArrayList<>();

        for(int i=0; i<100; i++){

            int num = i;
            Future<Integer> future = threadPool.submit(() -> {

                Thread.sleep(100);
                System.out.println("running:" + num);
                return num;
            });
            list.add(future);
        }

        /**
         * 获取结果
         */
        for (Future future : list){
            System.out.println("runned:" + future.get());
        }

    }

}
