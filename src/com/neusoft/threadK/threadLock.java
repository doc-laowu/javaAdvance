package com.neusoft.threadK;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class threadLock {
	public static void main(String[] args) {
		new Thread(new myThread(1)).start();
		new Thread(new myThread(2)).start();
	}
}

class myThread implements Runnable
{
	private int id;
	
	private static int count = 40;
	
	public myThread(int id) {
		this.id = id;
	}
	
	private Lock lock = new ReentrantLock();

	@Override
	public void run() {
		readOption();
		writeOption();
	}
	
	public void readOption()
	{
		for(int i=0; i<20; i++) 
		{
			lock.lock();
			System.out.println("Thread id:"+id+"执行读操作"
					+myThread.count+": "
					+System.currentTimeMillis());
			lock.unlock();
		}
	}
	
	public void writeOption()
	{
		for(int i=0; i<20; i++)
		{
			lock.lock();
			System.out.println("Thread id:"+id+"执行写操作"
				+ --myThread.count+": "
				+System.currentTimeMillis());
			lock.unlock();
		}
	}
	
}