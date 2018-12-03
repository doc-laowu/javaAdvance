package com.neusoft.threadK;

public class ThreadDemo01 {
	public static void main(String[] args) {
		trainStation ptr = new trainStation();
		new Thread(ptr).start();
		new Thread(ptr).start();
		new Thread(ptr).start();
	}
}

class trainStation implements Runnable
{
	//private volatile int tickets = 10;  //在并发条件下，不是绝对的线程安全
	
	private int tickets = 10;

	@Override
	public void run() {
		while(tickets > 0)
			{
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("卖出一张火车票!车票号为:"+tickets--);
			}
	}

//	@Override
//	public void run() {
//		synchronized (this) {
//			while(tickets > 0)
//			{
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//				System.out.println("卖出一张火车票!车票号为:"+tickets--);
//			}
//		}
//	}
	
//	@Override
//	public synchronized void run() {
//		//SoldTicket();
//		while(tickets > 0)
//		{
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			System.out.println("卖出一张火车票!车票号为:"+tickets--);
//		}
//	}
	
	
}
