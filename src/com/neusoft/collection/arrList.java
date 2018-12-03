package com.neusoft.collection;

import java.util.*;

public class arrList {
	
	public static void main(String[] args) {
		arrList ptr = new arrList();
		ptr.addGroups();
		ptr.startToEnd();
		ptr.endToStart();
	}
	
	private Collection<Integer> colletction = null;
	
	private List<Integer> list = null;
	
	public void addGroups()
	{
		colletction = 
				new ArrayList<Integer>(Arrays.asList(1,2,3,4,5));
		
		Integer[] moreInts = {6,7,8,9,10};
		
		colletction.addAll(Arrays.asList(moreInts));
		
		Collections.addAll(colletction,11,12,13,14,15);
		Collections.addAll(colletction,moreInts);
		
		list = new ArrayList<Integer>(Arrays.asList(110,111,112,113,114,115,116));
		
	}
	
	public void startToEnd()
	{
		//Iterator可以双向遍历
		
		Iterator<Integer> itr = colletction.iterator();
		while(itr.hasNext())
		{
			System.out.println( itr.next());
		}
	}
	
	public void endToStart()
	{
		//ListIterator可以双向遍历
		
		ListIterator<Integer> itr = list.listIterator(list.size());
		while(itr.hasPrevious())
		{
			System.out.println( itr.previous());
		}
	}
}
