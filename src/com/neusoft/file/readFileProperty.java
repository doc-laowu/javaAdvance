package com.neusoft.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class readFileProperty {

	public static void main(String[] args) throws IOException {
		
//		File file = new File("text.txt");
//		System.out.println("文件是否存在"+file.exists());
//		System.out.println("文件名称"+file.getName());
//		System.out.println("文件路径"+file.getPath());
//		System.out.println("文件绝对路径"+file.getAbsolutePath());
//		System.out.println("文件绝对路径"+new File(file.getAbsolutePath()).getParent());
//		System.out.println("文件大小"+file.length()/1000.0+"kb");
//		System.out.println("文件是否被隐藏"+file.isHidden());
//		System.out.println("文件是否可读"+file.canRead());
//		System.out.println("文件是否可写"+file.canWrite());
//		System.out.println("文件是否为文件夹"+file.isDirectory());
		
//		if(file.exists())
//		{
//			file.setWritable(true);
//			file.setReadable(false);
//			file.setReadOnly();
//		}
		
		
//		new readFileProperty().printFiles(new File("E:\\LearnFile"), 3);
		
//		new readFileProperty().fileRead("text.txt");
		
		new readFileProperty().fileRead("new_text.txt", "bak.txt");
	}
	
	public void printFiles(File dir, int tab)
	{
		if(dir.isDirectory())
		{
			for(int i=0; i<tab; i++)
				System.out.print(".");
			File[] next = dir.listFiles();
			for(int i=0; i<next.length; i++)
			{
				System.out.println(next[i].getName());
				if(next[i].isDirectory())
					printFiles(next[i], tab);
			}
		}
	}
	
	public void fileRead(String srcFile, String dstFile) throws IOException
	{
		File src = new File(srcFile);
		File dst = new File(dstFile);
		
		if(src.exists())
		{			
			BufferedReader bur = new BufferedReader(
					new InputStreamReader(new FileInputStream(src), "utf-8"));
			
			PrintWriter pw = new PrintWriter(
					new OutputStreamWriter(new FileOutputStream(dst), "utf-8"));
			
			String line;
			while((line = bur.readLine()) != null)
			{
				pw.println(line);
			}
			
			pw.flush();
			
			bur.close();
			pw.close();
			
		}
	}
	
	public void fileWrite(String filename) throws IOException
	{
		File file = new File(filename);
		BufferedWriter buw = new BufferedWriter(
				new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
		
		buw.write("****************************\r\n" +
				"钗留一股合一扇，钗擘黄金合分钿。 \r\n" + 
				"但教心似金钿坚，天上人间会相见。 \r\n" + 
				"临别殷勤重寄词，词中有誓两心知。 \r\n" + 
				"七月七日长生殿，夜半无人私语时。 \r\n" + 
				"在天愿作比翼鸟，在地愿为连理枝。 \r\n" + 
				"天长地久有时尽，此恨绵绵无绝期。");
		
		buw.close();
	}
}
