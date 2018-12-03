package com.neusoft.pattern;

import java.util.regex.*;

/**
 * 
 * @author Admin
 * 	time: 11点11分 2018/7/31
 * 	description : java的正则表达式
 *
 */


public class RegexExample1 {
	
	/**
	 * 	统计某个字符出现是否存在字符串中
	 */
	public void matchTelPhone()
	{
		String s = "(212) 555-1212 212-555-1212 (313) 444-1234";
		
		Pattern pattern = Pattern.compile("\\(\\d{3}\\)\\s\\d{3}-\\d{4}");
		
		Matcher matcher = pattern.matcher(s);
		
		if(matcher.find())
		{
			System.out.println("是否存在电话号码:"+matcher.matches());
		
			System.out.println("电话号码:"+matcher.group());
		}
		
	}
	
	/**
	 * 	统计某个某个字符出现的次数
	 */
	public void apperenceCount()
	{
		final String REGEX = "\\bcat\\b";
	    final String INPUT = "cat cat cat cattie cat";

	    Pattern p = Pattern.compile(REGEX);
        Matcher m = p.matcher(INPUT); // 获取 matcher 对象
        int count = 0;
 
        while(m.find()) 
        {
          count++;
          System.out.println("Match number "+count);
          System.out.println("start(): "+m.start());
          System.out.println("end(): "+m.end());
        }
	}
	/**
	 * lookAt是从头开始匹配，是否完全匹配。
	 * matches是完全匹配，如果字符串不是完全一样，就不能匹配。
	 */
	public void lookatPattern()
	{
		final String REGEX = "foo";
	    final String INPUT = "fooooooooooooooooo";
	    final String INPUT2 = "ooooofoooooooooooo";
	    Pattern pattern;
	    Matcher matcher;
	    Matcher matcher2;
        pattern = Pattern.compile(REGEX);
        matcher = pattern.matcher(INPUT);
        matcher2 = pattern.matcher(INPUT2);
 
        System.out.println("Current REGEX is: "+REGEX);
        System.out.println("Current INPUT is: "+INPUT);
        System.out.println("Current INPUT2 is: "+INPUT2);
 
 
        System.out.println("lookingAt(): "+matcher.lookingAt());
        System.out.println("matches(): "+matcher.matches());
        System.out.println("lookingAt(): "+matcher2.lookingAt());
	}
	
	public void checkQQ()
	{
		String qq = "0123125";
		String reg = "[1-9][0-9]{4,14}";                  
        System.out.println(qq.matches(reg)?"合法qq":"非法qq");         
	}
	
	public static void main(String[] args) {
		
		RegexExample1 ptr = new RegexExample1();
		//ptr.apperenceCount();
		//ptr.matchTelPhone();
		ptr.lookatPattern();
	}
}



