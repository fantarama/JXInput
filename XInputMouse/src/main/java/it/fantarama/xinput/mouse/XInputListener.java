package it.fantarama.xinput.mouse;

import com.microsoft.xinput.*;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Date;

/**
 * Created by Fantarama on 29/08/2016.
 */
public class XInputListener implements Runnable {

    private final Robot robot;

    private static final int POSITION_TICK = 1;
    private static final int MOVE_THRESOLD = 20000;

    public XInputListener() throws AWTException {
        robot = new Robot();
    }

    @Override
    public void run() {

        try {

            XInput xinput = XInputFactory.getInstance();
            int oldPacket = Integer.MIN_VALUE;
            XInputState state = new XInputState();

            long oldTime = new Date().getTime();
            Integer activeButton = null;
            double wheel = 0;

            do {

                int result = xinput.XInputGetState(0, state);
                if (result == XInput.ERROR_SUCCESS) {
                    if (state.isStateChanged(oldPacket)) {
                        if (state.Gamepad.isPressed(GamepadButton.A) && activeButton == null) {
                            robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
                            activeButton = InputEvent.BUTTON1_DOWN_MASK;
                        } else if (state.Gamepad.isPressed(GamepadButton.B) && activeButton == null) {
                            robot.mousePress(InputEvent.BUTTON3_DOWN_MASK);
                            activeButton = InputEvent.BUTTON3_DOWN_MASK;
                        } else if(state.Gamepad.getPressedButtons().isEmpty() && activeButton != null) {
                            robot.mouseRelease(activeButton);
                            activeButton = null;
                        }
                        if (state.Gamepad.isPressed(GamepadButton.BACK)) {
                            System.out.println("Back button pressed, EXIT!");
                            xinput = null;
                        }
                    }

                    long time = new Date().getTime();
                    if (time - oldTime >= 2) {
                        int x = (int) MouseInfo.getPointerInfo().getLocation().getX();
                        int y = (int) MouseInfo.getPointerInfo().getLocation().getY();
                        boolean changed = false;
                        if (state.Gamepad.sThumbLX < -MOVE_THRESOLD) {
                            x -= POSITION_TICK;
                            changed = true;
                        }
                        if (state.Gamepad.sThumbLX > MOVE_THRESOLD) {
                            x += POSITION_TICK;
                            changed = true;
                        }
                        if (state.Gamepad.sThumbLY < -MOVE_THRESOLD) {
                            y += POSITION_TICK;
                            changed = true;
                        }
                        if (state.Gamepad.sThumbLY > MOVE_THRESOLD) {
                            y -= POSITION_TICK;
                            changed = true;
                        }

                        if (state.Gamepad.sThumbRY > MOVE_THRESOLD) {
                            wheel += .08;
                            if(wheel >= 1) {
                                robot.mouseWheel(-1);
                                wheel = 0;
                            }
                        } else if (state.Gamepad.sThumbRY < -MOVE_THRESOLD) {
                            wheel -= .08;
                            if(wheel <= -1) {
                                robot.mouseWheel(1);
                                wheel = 0;
                            }
                        } else {
                            wheel = 0;
                        }
                        oldTime = time;
                        if (changed) {
                            robot.mouseMove(x, y);
                        }
                    }

                    oldPacket = state.dwPacketNumber;
                }

            } while (xinput != null);


        } catch (XInputLibraryNotFound e) {
            System.out.println("XInput library not installed on this develop machine");
        }
    }
}
