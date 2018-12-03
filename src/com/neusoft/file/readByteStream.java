package com.neusoft.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
public class readByteStream {

	public static void main(String[] args) {
		//readByteStream ptr = new readByteStream();
		//ptr.writeFile("new_text.txt");
		//ptr.copyBybyteStream("text.txt", "new_file.txt");
		//ptr.copyBycharStream("text.txt", "new_file.txt");
	
		
		
	}
	
	public void copyBybyteStream(String src, String dst)
	{
		try {
			FileInputStream fis = new FileInputStream(src);
			FileOutputStream fos = new FileOutputStream(dst);
			
			BufferedInputStream bufin = new BufferedInputStream(fis);
			BufferedOutputStream bufout = new BufferedOutputStream(fos);
			byte[] b = new byte[1024];
			int len = -1;
			while((len = bufin.read(b)) != -1)
			{
				bufout.write(b, 0, len);
			}
			
			bufout.flush();
			bufin.close();
			bufout.close();
			fis.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void copyBycharStream(String src, String dst)
	{
		try (FileInputStream fis = new FileInputStream(src);
			FileOutputStream fos = new FileOutputStream(dst);
			
			InputStreamReader isr = new InputStreamReader(fis, "utf-8");
			OutputStreamWriter isw = new OutputStreamWriter(fos, "utf-8");
			
			BufferedReader bufr = new BufferedReader(isr);
			//BufferedWriter bufw = new BufferedWriter(isw);
			PrintWriter pw = new PrintWriter(isw, true);
			){
			String str = null;
			while((str = bufr.readLine()) != null)
			{
				//bufw.write(str+"\r\n");
				pw.println(str);
			}
			
			isr.close();
			isw.close();
			fis.close();
			fos.close();
			bufr.close();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readFile(String filename)
	{
		try {
			FileInputStream fis = new FileInputStream(filename);
			byte[] b = new byte[1024*10];
			while(fis.read(b) != -1)
			{
				System.out.println(new String(b, "utf-8"));
			}
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void writeFile(String filename)
	{
		try {
			FileOutputStream fos = new FileOutputStream(filename);
			fos.write(new String("hello, 这是一个通过fileOutput数组写入文件").getBytes());
			fos.close();
			System.out.println("写入文件完成!");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void fileReadWrite(String src, String dst)
	{
		try {
			FileReader fr = new FileReader(src);
			FileWriter fw = new FileWriter(dst);
			BufferedReader bufr = new BufferedReader(fr);
			BufferedWriter bufw = new BufferedWriter(fw);
			
			String str = null;
			while((str = bufr.readLine()) != null)
			{
				bufw.write(str);
			}
			bufw.flush();
			
			fr.close();
			fw.close();
			bufr.close();
			bufw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}




















