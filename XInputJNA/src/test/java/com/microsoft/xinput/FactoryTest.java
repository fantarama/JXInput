package com.microsoft.xinput;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import org.junit.Test;

public class FactoryTest {

	@Test
	public void lookupTest() {
		
		// test xinput library instance creation
		
		try {
			
			XInput instance = XInputFactory.getInstance();
			assertNotNull(instance);
			
		} catch (XInputLibraryNotFound e) {
			fail("XInput library not installed on this develop machine");
		}
		
	}

}
