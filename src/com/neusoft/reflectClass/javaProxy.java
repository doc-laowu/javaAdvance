package com.neusoft.reflectClass;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 
 * @author Admin
 *	description : java动态代理模式
 *
 */

public class javaProxy {

	public static void main(String[] args) {
		
		CookManager cookManager = new CookManager(); 
		DynamicProxyHandler<ICook> dynamicProxyHandler = new DynamicProxyHandler(cookManager);
		ICook iCook =(ICook)Proxy.
				newProxyInstance(dynamicProxyHandler.getClass().getClassLoader(),
						cookManager.getClass().getInterfaces(), dynamicProxyHandler);
		//打印一下代理类的类名 
		System.out.println("代理类为:"+iCook.getClass().getName());
		iCook.dealWithFood();
		iCook.cook();

	}


//	public static void createProxyFile() throws IOException {
//
//		byte[] generateProxyClass = ProxyGenerator.generateProxyClass("$Proxy0", new Class<?>[]{ICook.class});
//
//		FileOutputStream outputStream = new FileOutputStream("$Proxy0.class");
//
//		outputStream.write(generateProxyClass);
//
//		outputStream.close();
//
//	}

}

interface ICook {

    void dealWithFood();

    void cook();
}

class CookManager implements ICook { 
	@Override 
	public void dealWithFood()
	{
		System.out.println("food had been dealed with"); 
	} 
	
	@Override 
	public void cook()
	{
		System.out.println("cook food"); 
	} 
} 

class DynamicProxyHandler<T> implements InvocationHandler
{ 
	T realCookManager;

	DynamicProxyHandler() {}

	
	DynamicProxyHandler(T realCookManager)
	{ 
		this.realCookManager = realCookManager; 
	}
	
	@Override 
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable 
	{ 
		System.out.println("invoke start");
		System.out.println("执行的方法为:"+method.getName());
		method.invoke(realCookManager,args);
		System.out.println("invoke end");
		return null; 
	}
} 



