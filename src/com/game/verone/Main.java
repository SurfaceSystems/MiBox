package com.game.verone;


import javax.swing.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Main {
    public static void main(String[] args) {
        int conection = 0;
        try {
            URL url = new URL("http://www.google.com");
            URLConnection connection = url.openConnection();
            connection.connect();
        } catch (MalformedURLException e) {
            conection = 1;
        } catch (IOException e) {
            conection = 1;
        }
        if(conection == 0) {
            System.out.println("CLIENT> Game MiBox x_1.0 started");
            try {
                new GameMotor();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showConfirmDialog(null, "The game requires internet connection to load sprites, connect to internet and try again", "Internet error", JOptionPane.OK_OPTION, JOptionPane.ERROR_MESSAGE);
        }
    }
}
