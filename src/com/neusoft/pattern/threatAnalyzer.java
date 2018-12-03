package com.neusoft.pattern;

import java.util.Scanner;
import java.util.regex.MatchResult;

public class threatAnalyzer {
	
	static String threatData = 
			"58.27.82,86@02/10/2018\n"+
			"58.27.82,86@02/10/2018\n"+
			"58.27.82,86@02/10/2018\n"+
			"58.27.82,86@02/10/2018\n"+
			"hahhahahahha";
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(threatData);
		String pattern = "(\\d+[.]\\d+[.]\\d+[.]\\d+)@"+
		"(\\d{2}/\\d{2}/\\d{4})";
		
		while(scan.hasNext(pattern))
		{
			scan.next(pattern);
			MatchResult result = scan.match();
			String ip = result.group(1);
			String date = result.group(2);
			System.out.println(ip+":"+date);
		}
		
		scan.close();
	}
}
