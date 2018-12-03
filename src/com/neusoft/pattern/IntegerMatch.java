package com.neusoft.pattern;

public class IntegerMatch {
	public static void main(String[] args) {
		//?表示前面可能存在某个字符
		System.out.println("-1234".matches("-?\\d+"));
		System.out.println("5678".matches("-?\\d+"));
		System.out.println("+911".matches("-?\\d+"));
		System.out.println("+911".matches("(-|\\+)?\\d+"));
	}
}
