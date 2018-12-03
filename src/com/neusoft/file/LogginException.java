package com.neusoft.file;


import java.util.logging.Logger;
import java.io.*;

@SuppressWarnings("serial")
public class LogginException extends Exception{
	
	private static Logger logger = Logger.getLogger("LogginException");
	
	public LogginException()
	{
		StringWriter trace = new StringWriter();
		printStackTrace(new PrintWriter(trace));
		logger.severe(trace.toString());
	}

	public static void main(String[] args) {
		try
		{
			throw new LogginException();
		}
		catch(LogginException e)
		{
			System.err.println("Caught"+e);
		}
		
		try
		{
			throw new LogginException();
		}
		catch(LogginException e)
		{
			System.err.println("Caught"+e);
		}
	}
}