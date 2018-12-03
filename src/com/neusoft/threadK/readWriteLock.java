package com.neusoft.threadK;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class readWriteLock {
	public static void main(String[] args) {
		
		new Thread(new bankThread(1)).start();
		new Thread(new bankThread(2)).start();
		new Thread(new bankThread(3)).start();
		
//		//获取cpu的核数
//		int cpuNums = Runtime.getRuntime().availableProcessors();
//		long maxMemory = Runtime.getRuntime().maxMemory();
//		
//		System.out.println(cpuNums);
//		System.out.println((double)maxMemory/1024/1024/1024);
		
	}
}

class bankThread implements Runnable
{
	private int id;
	
	static int money = 0;
	
	private ReadWriteLock lock = new ReentrantReadWriteLock();
	
	public bankThread(int id)
	{
		this.id = id;
	}
	
	@Override
	public void run() {
		for(int i=0; i<10; i++)
		{
			try {
				readMoney();
				Thread.sleep(10);
				reduceMoney(200);
				Thread.sleep(10);
				addMoney(2000);
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void addMoney(int crash)
	{
		lock.writeLock().lock();
		money += crash;
		lock.writeLock().unlock();
		System.out.println(id+": 成功存入"+crash+"元"+" "
				+System.currentTimeMillis());
		readMoney();
	}
	
	public void readMoney()
	{
		lock.readLock().lock();
		
		System.out.println(id+": 当前money余额为:"+money+" "
				+System.currentTimeMillis());
		
		lock.readLock().unlock();
	}
	
	public boolean isNull()
	{
		lock.readLock().lock();
		if(money <= 0)
		{
			lock.readLock().unlock();
			return true;
		}
		lock.readLock().unlock();
		return false;
		
	}
	
	public void reduceMoney(int crash)
	{
		if(isNull())
		{
			System.out.println(id+": 余额不足不能取钱了!"+" "
					+System.currentTimeMillis());
			return;
		}
		else
		{
			lock.writeLock().lock();
			money -= crash;
			lock.writeLock().unlock();
			System.out.println(id+": 成功取出"+crash+"元"+" "
					+System.currentTimeMillis());
			readMoney();
		}
	}
	
}