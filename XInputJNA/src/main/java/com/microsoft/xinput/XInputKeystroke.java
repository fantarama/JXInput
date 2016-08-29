package com.microsoft.xinput;

import java.util.Arrays;
import java.util.List;

import com.sun.jna.Structure;

/**
 * <p>
 * Specifies keystroke data returned by XInputGetKeystroke.
 * </p>
 * 
 * <p>
 * <code>typedef struct _XINPUT_KEYSTROKE {<br/>&nbsp;WORD VirtualKey;<br/>&nbsp;WCHAR Unicode;<br/>&nbsp;WORD
 * Flags;<br/>&nbsp;BYTE UserIndex;<br/>&nbsp;BYTE HidCode; <br/>} XINPUT_KEYSTROKE, *PXINPUT_KEYSTROKE;
 * </p>
 * </code>
 * 
 * @author andrea.fantappie@gmail.com
 *
 */
public final class XInputKeystroke extends Structure {

	/**
	 * Virtual-key code of the key, button, or stick movement. See XInput.h for
	 * a list of valid virtual-key (VK_xxx) codes. Also, see Remarks.
	 */
	public short VirtualKey;
	/**
	 * This member is unused and the value is zero.
	 */
	public String Unicode;
	/**
	 * Flags that indicate the keyboard state at the time of the input event.
	 * This member can be any combination of the XInput keystroke constants.
	 */
	public short Flags;
	/**
	 * Index of the signed-in gamer associated with the device. Can be a value
	 * in the range 0-3.
	 */
	public byte UserIndex;
	/**
	 * HID code corresponding to the input. If there is no corresponding HID
	 * code, this value is zero.
	 */
	public byte HidCode;

	@SuppressWarnings("rawtypes")
	@Override
	protected List getFieldOrder() {
		return Arrays.asList(new String[] { "VirtualKey", "Unicode", "Flags", "UserIndex", "HidCode" });
	}

}
