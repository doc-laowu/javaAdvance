package com.neusoft.file;

/**
 * 
 * @author Admin
 * time : 19点33分 2018/7/31
 * description : 自定义异常类
 *
 */

/**
 *     所有异常都必须是 Throwable 的子类。
  *     如果希望写一个检查性异常类，则需要继承 Exception 类。
  *   如果你想写一个运行时异常类，那么需要继承 RuntimeException 类。
 *
 */

//public class invalidPathException extends RuntimeException{
public class invalidPathException extends Exception{
	
	static final long serialVersionUID = 7818375828146090111L;
	
	public invalidPathException()
	{
		super();
	}
	
	public invalidPathException(String message)
	{
		super(message);
	}
	
	public invalidPathException(Throwable cause)
	{
		super(cause);
	}
	
	public invalidPathException(String message, Throwable cause)
	{
		super(message, cause);
	}
	
}
