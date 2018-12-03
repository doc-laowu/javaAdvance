package com.neusoft.collection;

import java.util.*;

public class IterableClass implements Iterable<String>{
	
	protected String[] words = ("And that is how"
	+"we know the erath to be the banana_shaped").split(" ");
	
	

	@Override
	public Iterator<String> iterator() {
		return new Iterator<String>() {
			
			private int index = 0;
			
			@Override
			public String next() {
				// TODO Auto-generated method stub
				return words[index++];
			}
			
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return index < words.length;
			}
		};
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		for(String x : new IterableClass())
//			System.out.println(x);
		
		//获取系统环境变量
//		for(Map.Entry entry : System.getenv().entrySet())
//			System.out.println(entry.getKey()+":"+entry.getValue());
		
	}

}
