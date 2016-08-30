# JXInput

Provide java access to [Microsoft XInput](https://msdn.microsoft.com/en-us/library/windows/desktop/ee417001(v=vs.85).aspx) Game Controller API that enables applications to receive input from the Xbox 360 Controller for Windows.

The **XInputJNA** module is the core library that wrap and expose the native XInput API through JNA.

The **XInputMouse** module is a simple daemon that allow to control the mouse pointer with gamepad:
- Left thumb stick move the mouse cursor
- A button is bound to the left mouse button
- B button is bound to the right mouse button
- Press the BACK button to stop the daemon


**NOTE**

The Microsoft XInput driver library must be already installed on host pc

The XInputMouse listen for the 0 index gamepad, so, if multiple gamepads are connected, only the first is able to control the mouse
