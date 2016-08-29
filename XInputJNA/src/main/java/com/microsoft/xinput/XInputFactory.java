package com.microsoft.xinput;

import static com.microsoft.xinput.XInput.XINPUT_DLL_1_3;
import static com.microsoft.xinput.XInput.XINPUT_DLL_1_4;
import static com.microsoft.xinput.XInput.XINPUT_DLL_9_1_0;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.sun.jna.Native;

/**
 * Factory class for XInput library instance
 * 
 * @author andrea.fantappie@gmail.com
 */
public final class XInputFactory {

	public static final String DEFAULT_WINDOWS_PATH = "c:\\windows";
	public static final String WINDOWS_PATH_PROPERTY = "os.arch.dir";

	private static final String WIN32 = "System32";
	private static final String WIN64 = "SysWOW64";

	/**
	 * <p>
	 * Create and return a new XInput JNA library. It lookup to the local
	 * library dll on default Windows path (see DEFAULT_WINDOWS_PATH constant)
	 * or set <code>os.arch.dir</code> system property to provide you custom
	 * location.
	 * </p>
	 * 
	 * <p>
	 * The XInput avaiable functions depends on which library version is
	 * installed; to call a function not defined in older versions cause a
	 * runtime exception.
	 * </p>
	 * 
	 * <p>
	 * See Microsoft XInput reference to know how to install the XInput SDK.
	 * </p>
	 * 
	 * @return XInput instance
	 * 
	 * @throws XInputLibraryNotFound
	 *         if library dll are not found
	 */
	public static XInput getInstance() throws XInputLibraryNotFound {

		String dir = System.getProperty(WINDOWS_PATH_PROPERTY);
		if (dir == null) {
			dir = DEFAULT_WINDOWS_PATH;
		}
		
		String vmType = System.getProperty("sun.arch.data.model");

		Path dll = Paths.get(dir, vmType.equals("64") ? WIN64 : WIN32);

		// dll lookup
		if (Files.exists(dll.resolve(XINPUT_DLL_1_4))) {
			System.out.println("XInput library found at " + dll.resolve(XINPUT_DLL_1_4).toString());
			System.setProperty("jna.library.path", dll.resolve(XINPUT_DLL_1_4).toString());
			return (XInput) Native.loadLibrary(XINPUT_DLL_1_4, XInput.class);
		} else if (Files.exists(dll.resolve(XINPUT_DLL_1_3))) {
			System.out.println("XInput library found at " + dll.resolve(XINPUT_DLL_1_3).toString());
			System.setProperty("jna.library.path", dll.resolve(XINPUT_DLL_1_3).toString());
			return (XInput) Native.loadLibrary(XINPUT_DLL_1_3, XInput.class);
		} else if (Files.exists(dll.resolve(XINPUT_DLL_9_1_0))) {
			System.out.println("XInput library found at " + dll.resolve(XINPUT_DLL_9_1_0).toString());
			System.setProperty("jna.library.path", dll.resolve(XINPUT_DLL_9_1_0).toString());
			return (XInput) Native.loadLibrary(XINPUT_DLL_9_1_0, XInput.class);
		} else {
			throw new XInputLibraryNotFound("XInput library not found in " + dll.toString());
		}

	}

}
