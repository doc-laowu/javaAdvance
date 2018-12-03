package com.neusoft.collection;

import java.util.*;

public class mapTest {
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
	    map.put("1", "value1");
	    map.put("2", "value2");
	    map.put("3", "value3");
	    
	    //foreach遍历方式
	    for(String key : map.keySet())
	    {
	    	System.out.println("key= "+ key + " and value= " + map.get(key));
	    }
	    
	  //通过迭代器方式
      System.out.println("通过Map.entrySet使用iterator遍历key和value：");
      Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
      while (it.hasNext()) {
	      Map.Entry<String, String> entry = it.next();
	      System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
      }

    //先获取到(key,value)对象的集合，然后再对集合通过key搜索value
      System.out.println("通过Map.entrySet遍历key和value");
      for (Map.Entry<String, String> entry : map.entrySet()) {
    	  System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
      }
     
    //通过foreach的方式，直接遍历value
      System.out.println("通过Map.values()遍历所有的value，但不能遍历key");
      for (String v : map.values()) {
    	  System.out.println("value= " + v);
      }

	}
}
