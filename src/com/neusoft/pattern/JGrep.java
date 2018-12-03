package com.neusoft.pattern;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
//import net.mindView.util.TextFile;

/**
 * 
 * @author Admin
 *	description : 实现类似linux的grep模糊查找的效果
 *	此处实现的是wordCount功能
 */

public class JGrep {
	public static void main(String[] args) {
		
		String regex = ""; 
		String filename = "";
		
		Pattern p = Pattern.compile(regex);
		int index = 0;
		Matcher m = p.matcher("");
//		for(String line : new TextFile(filename))
		{
//			m.reset(line);
			while(m.find())
			{
				System.out.println(index++ + ": "+m.group()+": "+m.start());
			}
		}
		
	}
}
