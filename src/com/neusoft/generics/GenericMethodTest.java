package com.neusoft.generics;

public class GenericMethodTest<T> {
	
	private T a;
	private T b;
	
	GenericMethodTest(T a, T b)
	{
		this.a = a;
		this.b = b;
	}
	
	public T getA() {
		return a;
	}
	public void setA(T a) {
		this.a = a;
	}
	public T getB() {
		return b;
	}
	public void setB(T b) {
		this.b = b;
	}
	
	
	public static void main(String[] args) {
//		GenericMethodTest<Integer> ptr = new GenericMethodTest<Integer>(100,101);
//		
//		System.out.println("a:"+ptr.getA()+" b:"+ptr.getB());
		
		Integer[] intArray = { 1, 2, 3, 4, 5 };
		Double[] doubleArray = { 1.1, 2.2, 3.3, 4.4 };
		Character[] charArray = { 'H', 'E', 'L', 'L', 'O' };
		
		genericClass.printArray(intArray);
		genericClass.printArray(doubleArray);
		genericClass.printArray(charArray);
		
		
		genericClass.sayHello("这是我的第一个泛型函数使用例子!");
	}
}

class genericClass
{
	// 泛型方法 printArray                         
	public static < E > void printArray( E[] inputArray )
	{
		// 输出数组元素            
		for ( E element : inputArray ){        
	    System.out.printf( "%s ", element );
	     
		}
		System.out.println();
	}
	
	public static <T> void sayHello(T str)
	{
		System.out.println(str);
	}

}



























