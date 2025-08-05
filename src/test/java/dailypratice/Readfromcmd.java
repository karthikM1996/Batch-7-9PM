package dailypratice;

import org.testng.annotations.Test;

public class Readfromcmd {

	@Test
	public void readfromcmd1()
	{
		String URL = System.getProperty("url");
		String USERNAME = System.getProperty("username");
		String PASSWORD = System.getProperty("password");
		System.out.println(URL);
		System.out.println(USERNAME);
		System.out.println(PASSWORD);
		
		
		
	}
}
