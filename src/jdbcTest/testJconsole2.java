package jdbcTest;

public class testJconsole2 {

	public static void main(String[] args) throws InterruptedException {
		
		Object ptr = null;
		
		for(int i=0; i<2000; i++)
		{
			ptr = new Object();
			System.out.println(i+":生成了一个对象!");
			Thread.sleep(500);
			ptr = null;
		}
		
	}
	
}
