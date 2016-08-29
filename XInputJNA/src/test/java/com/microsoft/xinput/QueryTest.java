package com.microsoft.xinput;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

/**
 * Test class for base state query functions, it need one connected controller to pass
 *  
 * @author andrea.fantappie@gmail.com
 *
 */
public class QueryTest {
	
	@Test
	public void connectionTest() {
		
		XInput instance;
		try {
			
			instance = XInputFactory.getInstance();
			
			int result = instance.XInputGetState(0, new XInputState());
			assertEquals(XInput.ERROR_SUCCESS, result);
			
		} catch (XInputLibraryNotFound e) {
			fail("XInput library not installed");
		}
		
	}

}
