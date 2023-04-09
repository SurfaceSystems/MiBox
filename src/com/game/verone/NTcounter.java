package com.game.verone;

public class NTcounter {
    public static int checkLoop(int min, int max, int value) {
        int retnum = value;
        if(value > max) {
            retnum = min;
        }
        if(value < min) {
            retnum = max;
        }
        return retnum;
    }
    public static int RGBA(int r, int g, int b, int a) {
        return (a << 24) | ((r & 255) << 16) | ((g & 255) << 8) | (b & 255);
    }
}
