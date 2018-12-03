package com.neusoft.OpSocket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class chatSocket extends Thread {

	private Socket sock = null;
	
	public chatSocket(Socket sock)
	{
		this.sock = sock;
	}
	
	public void out(String out)
	{
		try {
			sock.getOutputStream().write(out.getBytes("utf-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(sock.getInputStream(), "utf-8"));
			String line = null;
			while((line = br.readLine()) != null)
			{
				chatManager.getInstance().publish(this, line);
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
//	@Override
//	public void run() {
//		try {
//			
//			BufferedWriter bw = 
//					new BufferedWriter(
//							new OutputStreamWriter(sock.getOutputStream()));
//			int count = 0;
//			while(true)
//			{
//				bw.write("loop"+count);
//				try {
//					Thread.sleep(1000);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}
