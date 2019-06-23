package io.github.zhengyhn.practice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Random;

public class RandomInteger {
    private static final Random rnd = new Random();

    public static void main(String[] args) {
        int n = 1 * (Integer.MAX_VALUE / 5);
        System.out.println(n);
        int low = 0;
        int times = 10000;
        for (int i = 0; i < times; ++i) {
            if (random(n) < n / 2) {
                ++low;
            }
        }
        System.out.println((float)low / times);
    }

    private static int random(int upper) {
        return Math.abs(rnd.nextInt()) % upper;
    }
}
