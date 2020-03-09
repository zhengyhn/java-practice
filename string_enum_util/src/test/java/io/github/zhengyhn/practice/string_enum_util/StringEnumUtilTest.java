package io.github.zhengyhn.practice.string_enum_util;


import org.junit.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertEquals;

public class StringEnumUtilTest {
    enum TestEnum implements IStringEnum {
        ONE("one");
        TestEnum(String value) {
            this.value = value;
        }
        @Override
        public String getValue() {
            return this.value;
        }
        private String value;
    }

    @Test
    public void testFromStringMultiThreadShouldOk() {
        final int numThread = 1000;
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch doneLatch = new CountDownLatch(numThread);
        List<Boolean> resultList = Collections.synchronizedList(new LinkedList<>());
        for (int i = 0; i < numThread; ++i) {
            new Thread(() -> {
                try {
                    startLatch.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                resultList.add(StringEnumUtil.fromString(TestEnum.class, "one") != null);
                doneLatch.countDown();
            }).start();
        }
        startLatch.countDown();
        try {
            doneLatch.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(numThread, resultList.stream().filter(item -> item.booleanValue()).count());
    }
}