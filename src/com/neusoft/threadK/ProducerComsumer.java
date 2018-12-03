package com.neusoft.threadK;

import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProducerComsumer {

	public static void main(String[] args) {  

        Vector<Integer> sharedQueue = new Vector<Integer>();  

        int size = 4;  

        Thread prodThread = new Thread(new threadProducer(sharedQueue, size), "Producer");
        Thread prodThread2 = new Thread(new threadProducer(sharedQueue, size), "Producer");

        Thread consThread = new Thread(new threadComsumer(sharedQueue, size), "Consumer");  

        prodThread.start();
        prodThread2.start();
        consThread.start();  

    }
}

class threadProducer implements Runnable{
	
	private final Vector<Integer> Productions;
	private final int size;

	public threadProducer(Vector<Integer> Productions, int size)
	{
		this.Productions = Productions;
		this.size = size;
	}
	
	@Override
	public void run() {
		for (int i = 0; i < 30; i++) {
            try {  

                produce(i);  

            } catch (InterruptedException ex) {

                Logger.getLogger(threadProducer.class.getName()).log(Level.SEVERE, null, ex);  

            }

        }
	}
	
	private void produce(int i) throws InterruptedException {
        //wait if queue is full  

        while (Productions.size() == size) {  

            synchronized (Productions) {  

                System.out.println("Queue is full " + Thread.currentThread().getName()  

                        + " is waiting , size: " + Productions.size());  

                Productions.wait();  

            }  

        }
        //producing element and notify consumers  

        synchronized (Productions) {  

        	if(Productions.add(i))
        		System.out.println("Produced:" + i);
        	Productions.notifyAll();

        }  

    }
}

class threadComsumer implements Runnable
{
	private final Vector<Integer> sharedQueue;

	private final int SIZE;

	public threadComsumer(Vector<Integer> sharedQueue, int size) {  
	
	    this.sharedQueue = sharedQueue;  
	
	    this.SIZE = size;  
	
	}

	@Override  
	
	public void run() {  
	
	    // TODO Auto-generated method stub  
	
	    while (true) {  
	
	        try {  
	
	            System.out.println("Consumer: " + consume());  
	
	            Thread.sleep(50);  
	
	        } catch (InterruptedException ex) {  
	
	            Logger.getLogger(threadComsumer.class.getName()).log(Level.SEVERE, null, ex);  
	
	        }  
	
	    }  
	
	}

	private int consume() throws InterruptedException {
	    //wait if queue is empty  
	
	    while (sharedQueue.isEmpty()) {  
	
	        synchronized (sharedQueue) {  
	
	            System.out.println("Queue is empty " + Thread.currentThread().getName()  
	
	                    + " is waiting , size: " + sharedQueue.size());  
	
	            sharedQueue.wait();
	
	        }
	    }
	    //otherwise consume element and notify waiting producer  
	
	    synchronized (sharedQueue) {  
	
	        sharedQueue.notifyAll();  
	
	        return sharedQueue.remove(0);  
	
	    }
    }
}


