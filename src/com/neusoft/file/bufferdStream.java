package com.neusoft.file;

import java.io.*;

public class bufferdStream {
	
	public static void main(String[] args) {
		
//			new bufferdStream().MediaFileCopy("C:\\Users\\Admin.DESKTOP-HM7VOBS\\Pictures\\Saved Pictures\\02.jpg",
//				"C:\\Users\\Admin.DESKTOP-HM7VOBS\\Pictures\\02.jpg");
		
			try {
				new bufferdStream().MediaFileCopy("", "");
			} catch (invalidPathException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public void MediaFileCopy(String src, String dst) throws invalidPathException
	{
		if(src.isEmpty() || dst.isEmpty())
			throw new invalidPathException("源文件为空!");
		
		BufferedInputStream in = null;
		BufferedOutputStream out = null;
		
		int len;

		try {
			in = new BufferedInputStream(new FileInputStream(src));
			out = new BufferedOutputStream(new FileOutputStream(dst));
			
			while((len = in.read()) != -1)
			{
				out.write(len);
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("文件拷贝成功!");
		}

	}
}
