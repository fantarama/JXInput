package it.fantarama.xinput.mouse;

import java.awt.*;

/**
 * Created by Fantarama on 29/08/2016.
 */
public class Daemon {


    public static void main(String[] args) {

        System.out.println("Starting XInput mouse daemon");

        XInputListener listener = null;
        try {
            listener = new XInputListener();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        new Thread(listener).start();

    }
}
