package com.neusoft.reflectClass;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class servicesServer {
	public static void main(String[] args) throws IOException {
		
		//ExecutorService threadpool = Executors.newFixedThreadPool(4);
		
		ServerSocket server = new ServerSocket();
		server.bind(new InetSocketAddress("localhost", 10086));
		while(true)
		{
			Socket socket = server.accept();
			//threadpool.submit(new servicesManager(socket));
			if(socket != null)
				System.out.println("一个客户端连接上来了!");
			
			new Thread(new servicesManager(socket)).start();
		}
	}
}

class servicesManager implements Runnable
{
	private Socket socket = null;
	
	public servicesManager(Socket socket)
	{
		this.socket = socket;
	}
	
	@Override
	public void run() {
		
		OutputStream os = null;
		InputStream is = null;
		
		System.out.println("线程运行中!");
		
		try {
			
			os = socket.getOutputStream();
			
			is = socket.getInputStream();
			
			BufferedReader br = new BufferedReader(
					new InputStreamReader(is));
			
			PrintWriter pw = new PrintWriter(os);
			
			String param = br.readLine();
			
			System.out.println(param);
			
			pw.println( (new services()).returnService(param));
			pw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
					if(is != null)
							is.close();
					if(os != null)
						os.close();
					if(socket != null)
						socket.close();
				} catch (IOException e) {
						e.printStackTrace();
			}
		}
	}
}

class services
{
	public String returnService(String param)
	{
		return param+"客户端的返回结果";
	}
}

















