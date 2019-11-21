package com.neusoft.threadK;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class threadPool1 {
	public static void main(String[] args) {
		
		ExecutorService threadpool = Executors.newCachedThreadPool();
		
		for(int i=0; i<20; i++)
		{
			threadpool.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep(50);
						System.out.println(Thread.currentThread().getName()+":"
							+System.currentTimeMillis());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			});
		}
		
		threadpool.shutdown();
		
	}
}
