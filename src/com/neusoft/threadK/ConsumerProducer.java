package com.neusoft.threadK;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


/**
 * 
 * @author Admin
 *	description : 通过BlockingQueue来实现，生产者消费者模式	
 *
 */

public class ConsumerProducer {

	public static void main(String[] args) {
		
		final BlockingQueue<String> bq = new ArrayBlockingQueue<String>(10);
	    Thread producerThread = new Thread(new Producer(bq, 1));
	    Thread producerThread2 = new Thread(new Producer(bq, 1));
	    Thread producerThread3 = new Thread(new Producer(bq, 1));
	    Thread customerThread = new Thread(new Consumer(bq));
	    producerThread.start();
	    producerThread2.start();
	    producerThread3.start();
	    customerThread.start();
	}
}

class Producer implements Runnable
{
	private static int i;
	
	private final BlockingQueue<String> bq;
	
	public Producer(BlockingQueue<String> bq, int i)
	{
		this.bq = bq;
		Producer.i = i;
	}
	
	@Override
	public void run() {
		while (true)
        {
			synchronized (this)
			{
	            try
	            {
	            	bq.put(i + "");
	                System.out.println("我生产了一个" + i++ +" SIZE:"+bq.size());
	                Thread.sleep(1000);
	            } 
	            catch (InterruptedException e)
	            {
	                e.printStackTrace();
	            }
			}
        }
	}
	
}

class Consumer implements Runnable
{
	private final BlockingQueue<String> bq;
	
	public Consumer(BlockingQueue<String> bq)
	{
		this.bq = bq;
	}
	
	@Override
	public void run() {
		while (true)
        {
            try
            {
                System.out.println("我消费了一个" + bq.take()+" SIZE:"+bq.size());
                Thread.sleep(2000);
            } 
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
	}
	
}






