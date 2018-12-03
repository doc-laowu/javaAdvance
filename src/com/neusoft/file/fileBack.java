package com.neusoft.file;

import java.io.*;

public class fileBack {
	
	public void backFile(String srcPath, String dstPath)
	{
		byte[] buff = new byte[1024];
		
		InputStream inputStream = null;
		
		OutputStream outputStream = null;
		
		int len;
		
		try 
		{
			inputStream = new FileInputStream(srcPath);
			
			outputStream = new FileOutputStream(dstPath);
			
			while((len = inputStream.read(buff)) != -1)
			{
				outputStream.write(buff, 0, len);
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		} 
		finally 
		{
			try 
			{
				inputStream.close();
				outputStream.close();
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			finally
			{
				System.out.println("文件备份成功!");
			}
			
		}
	}
	
	public void readFileContent(String srcPath)
	{
		BufferedReader in = null;
		//char[] buff = new char[100];
		String str;
		try 
		{
			in = new BufferedReader(new FileReader(srcPath));
			
			try {
				while((str = in.readLine()) != null)
				{
					System.out.println(str);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				in.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
	
	public void writeFileContent(String dstString)
	{
		BufferedWriter out = null;
		
		try 
		{
			out = new BufferedWriter(new FileWriter(dstString));
			
			out.write("大家好！我是老吴，这是我第一次使用fileWriter写入文件内容");
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				out.flush();
				out.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			}
			System.out.println("写入文件成功!");
		}
	}
	
	
	public static void main(String[] args) {
		//fileBack ptr = new fileBack();
		//ptr.backFile("E:\\LearnFile\\Java\\note.txt", "E:\\LearnFile\\java_note.txt");
		//ptr.readFileContent("E:\\LearnFile\\java_note.txt");
		//ptr.writeFileContent("E:\\LearnFile\\java_note.txt");
		
		int[] arr = {1,2,3};
		
		char[] x = new char[3];
		
		x[0] = 'x';
		x[1] = 'y';
		x[2] = 'z';

		System.out.println(arr);
	}
}
