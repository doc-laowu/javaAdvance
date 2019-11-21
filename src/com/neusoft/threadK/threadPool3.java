package com.neusoft.threadK;

import java.util.ArrayList;
import java.util.concurrent.*;

public class threadPool3 {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		Future<?> result = null;
		ArrayList<Future<?>> lists = new ArrayList<Future<?>>();
		
		ExecutorService threadpool3 = Executors.newFixedThreadPool(4);
		
		for(int i=0; i<20; i++)
		{
			result = threadpool3.submit(new Callable<String>() {

				@Override
				public String call() throws Exception {
					return Thread.currentThread().getName()+":"
							+System.currentTimeMillis();
				}
				
			});
			lists.add(result);
		}
		
		for(Future<?> result1 : lists)
		{
			System.out.println(result1.isDone()?result1.get():"没完成!");
		}
		
		threadpool3.shutdown();

	}
}
