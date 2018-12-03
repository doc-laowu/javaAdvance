package com.neusoft.threadK;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class threadpool4 {
	public static void main(String[] args) {
		
		ScheduledExecutorService threadpool4 = Executors.newScheduledThreadPool(4);
		
		for(int i=0; i<20; i++)
		{
//			threadpool4.submit(new Runnable() {
//				@Override
//				public void run() {
//					System.out.println(Thread.currentThread().getName()+
//							"启动时间:"+System.currentTimeMillis());
//				}
//			});
			
			threadpool4.schedule(new Runnable() {
				@Override
				public void run() {
					System.out.println(Thread.currentThread().getName()+
							"启动时间:"+System.currentTimeMillis());
				}
			}, (long) (Math.random()*10), TimeUnit.MILLISECONDS);
		}
		
		threadpool4.shutdown();
	}
}
