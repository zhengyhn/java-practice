package io.github.zhengyhn.practice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

public class ShutdownHook {
    private static final String LOCK_FILE = "./.lock";

    public static void main(String[] args) {
        checkOrCreateLockFile();
        addShutdownHook();

        while (true) {
            try {
                Thread.sleep(2000);
                System.out.println("程序执行中");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void checkOrCreateLockFile() {
        Path path = Paths.get(LOCK_FILE);
        if (Files.exists(path)) {
            throw new RuntimeException("程序已经启动了");
        }
        try {
            Files.createFile(path);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    private static void addShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
           System.out.println("删除.lock文件");
           try {
               Files.delete(Paths.get(LOCK_FILE));
           } catch (IOException e) {
               System.out.println(e.getMessage());
           }
        }));
    }
}
