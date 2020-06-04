package io.github.zhengyhn.practice.random_integer;

import java.util.concurrent.locks.Lock;

public class AlphaNumberPrint2 {
    private static volatile boolean lock;

    public static void main(String[] args) throws InterruptedException {
        Thread lowerThread = new Thread(() -> {
            for (int i = 0; i < 26;) {
                if (!lock) {
                    System.out.print((char)('a' + i));
                    lock = true;
                    ++i;
                }
            }
        });
        Thread upperThread = new Thread(() -> {
            for (int i = 0; i < 26;) {
                if (lock) {
                    System.out.print((char)('A' + i));
                    lock = false;
                    ++i;
                }
            }
        });
        lowerThread.start();
        upperThread.start();
        lowerThread.join();
        upperThread.join();
    }
}
