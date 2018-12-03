package com.neusoft.threadK;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 
 * @author Admin
 *	
 *	description : runnable和callable都是实现线程的接口，而只有callable有返回值
 */

public class threadPool2 {
	public static void main(String[] args) throws Exception, ExecutionException {
		
		ExecutorService threadpool2 = Executors.newFixedThreadPool(4);
		
		for(int i=0; i<20; i++)
		{
			Future<String> result= threadpool2.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					Thread.sleep(1000);
					return Thread.currentThread().getName()+":"
							+System.currentTimeMillis();
				}
			});
			//从线程种返回结果是通过回调方法实现的，所以这是一个阻塞的方法
			System.out.println(result.get());
		}
		threadpool2.shutdown();
	}
}
