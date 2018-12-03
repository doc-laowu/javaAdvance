package com.neusoft.OpSocket;

import java.util.Vector;

public class chatManager {
	private chatManager() {}
	
	private  static final chatManager cm = new chatManager();
	
	public static chatManager getInstance()
	{
		return cm;
	}
	
	Vector<chatSocket> arr = new Vector<chatSocket>();
	
	public void add(chatSocket sock)
	{
		arr.add(sock);
	}
	
	public void publish(chatSocket cs, String out)
	{
		for(int i=0; i<arr.size(); i++)
		{	
			chatSocket csChatSocket = arr.get(i);
			if(!cs.equals(csChatSocket))
			{
				cs.out(out);
			}
		}
	}
}