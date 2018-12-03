package com.neusoft.reflectClass;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class serviceClient {
	public static void main(String[] args) throws UnknownHostException, IOException {
		
		Socket socket = new Socket("localhost", 10086);
		
		OutputStream os = socket.getOutputStream();
		
		InputStream is = socket.getInputStream();
		
		PrintWriter pw = new PrintWriter(os);
		
		BufferedReader br = new BufferedReader(
				new InputStreamReader(is));
		
		String str = "hello world!";
		pw.println(str);
		pw.flush();
		
		System.out.println(br.readLine());
		
		os.close();
		is.close();
		socket.close();
	}
}
