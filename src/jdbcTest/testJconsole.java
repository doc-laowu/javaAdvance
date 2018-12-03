package jdbcTest;

import java.util.ArrayList;
import java.util.List;

public class testJconsole {
	
	public static void main(String[] args) throws InterruptedException {

		List<Object> list = new ArrayList<Object>();
		
		for(int i=0; i<2000; i++)
		{
			list.add(new Object());
			Thread.sleep(1000);
		}
		
		list.clear();
	}
}
