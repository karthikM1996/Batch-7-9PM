package testNGPratice;

import org.testng.Reporter;
import org.testng.annotations.Test;

public class Demo {
	
	@Test
	public void a11()
	{
		Reporter.log("a11 excueted",true);
	}
	
	@Test
	public void a23()
	{
		Reporter.log("a23 excueted",true);
	}
	
	@Test
	public void b25()
	{
		Reporter.log("b25 excueted",true);
	}

	@Test
	public void b11()
	{
		Reporter.log("b11 excueted",true);
	}
	
	@Test
	public void a16()
	{
		Reporter.log("a16 excueted",true);
	}



}
