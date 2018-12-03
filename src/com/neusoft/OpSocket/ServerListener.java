package com.neusoft.OpSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerListener extends Thread {

	@Override
	public void run() {
		try (ServerSocket serverSocket = new ServerSocket(12345);){
			while(true)
			{
				Socket socket = serverSocket.accept();
				chatSocket cs = new chatSocket(socket);
				cs.start();
				chatManager.getInstance().add(cs);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
