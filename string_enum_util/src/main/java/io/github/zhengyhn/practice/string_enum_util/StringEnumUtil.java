package io.github.zhengyhn.practice.string_enum_util;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class StringEnumUtil {
    private volatile static Map<String, Map<String, Enum>> stringEnumMap = new ConcurrentHashMap<>();

    private StringEnumUtil() {}

    public static <T extends Enum<T>> Enum fromString(Class<T> enumClass, String symbol) {
        final String enumClassName = enumClass.getName();
        stringEnumMap.computeIfAbsent(enumClassName, key -> {
            System.out.println("aaa:" + stringEnumMap.get(enumClassName));
            Map<String, Enum> innerMap = new ConcurrentHashMap<>();
            EnumSet<T> set = EnumSet.allOf(enumClass);
            for (Enum e: set) {
                if (e instanceof IStringEnum) {
                    innerMap.computeIfAbsent(((IStringEnum) e).getValue(), k -> e);
                }
            }
            return innerMap;
        });
//        if (!stringEnumMap.containsKey(enumClassName)) {
//            synchronized (enumClass) {
//                if (!stringEnumMap.containsKey(enumClassName)) {
//                    System.out.println("aaa:" + stringEnumMap.get(enumClassName));
//                    Map<String, Enum> innerMap = new HashMap<>();
//                    EnumSet<T> set = EnumSet.allOf(enumClass);
//                    for (Enum e: set) {
//                        if (e instanceof IStringEnum) {
//                            innerMap.put(((IStringEnum) e).getValue(), e);
//                        }
//                    }
//                    stringEnumMap.put(enumClassName, innerMap);
//                }
//            }
//        }
        return stringEnumMap.get(enumClassName).get(symbol);
    }
}
