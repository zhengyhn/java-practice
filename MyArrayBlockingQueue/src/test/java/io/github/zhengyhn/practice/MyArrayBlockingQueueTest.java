package io.github.zhengyhn.practice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MyArrayBlockingQueueTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void forSingleThread() throws Exception {
        MyArrayBlockingQueue<String> queue = new MyArrayBlockingQueue<>(3);
        queue.put("aaa");
        queue.put("bbb");
        queue.put("ccc");
        System.out.println(queue.take());
        queue.put("ddd");

        while (!queue.empty()) {
            System.out.println(queue.take());
        }
    }

    @Test
    public void forMultiThread() throws Exception {
        MyArrayBlockingQueue<String> queue = new MyArrayBlockingQueue<>(100);
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10; ++i) {
                queue.put(i + "");
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 10; i < 20; ++i) {
                queue.put(i + "");
            }
        });
        Thread t3 = new Thread(() -> {
            for (int i = 0; i < 2; ++i) {
                System.out.println(queue.take());
            }
        });
        t1.start();
        t2.start();
        t3.start();

        t1.join();
        t2.join();
        t3.join();

        System.out.println(queue.size());
    }

}