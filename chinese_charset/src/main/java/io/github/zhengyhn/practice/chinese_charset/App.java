package io.github.zhengyhn.practice.chinese_charset;

public class App {
    public static void main(String[] args) {
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(-2));

        String text = "选";
        try {
//            byte[] bytes = text.getBytes("GB2312");
//            byte[] bytes = text.getBytes("GBK");
            byte[] bytes = text.getBytes("GB18030");
            for (byte b: bytes) {
                System.out.println(b & 0xff);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
