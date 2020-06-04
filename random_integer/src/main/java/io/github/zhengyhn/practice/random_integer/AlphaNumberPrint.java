package io.github.zhengyhn.practice.random_integer;

public class AlphaNumberPrint {
    private static class Value {
        public char c;
        public boolean lock;
        public Value(char c) {
            this.c = c;
        }
    }

    private static class MyThread extends Thread {
        private Value value;
        public MyThread(Value v) {
            this.value = v;
        }

        @Override
        public void run() {
            try {
                execute();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        private void execute() throws InterruptedException {
            while (this.value.c != 'z' && this.value.c != 'Z') {
                synchronized (this.value) {
                    if (this.value.c >= 'a' && this.value.c <= 'z') {
                        while (this.value.lock) {
                            this.value.wait();
                        }
                        System.out.print(this.value.c);
                        this.value.c += ('A' - 'a');
                        this.value.lock = true;
                        this.value.notify();
                    } else {
                        while (!this.value.lock) {
                           this.value.wait();
                        }
                        System.out.print(this.value.c);
                        this.value.c += 1 + ('a' - 'A');
                        this.value.lock = false;
                        this.value.notify();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Value value = new Value('a');
        MyThread lowerThread = new MyThread(value);
        MyThread upperThread = new MyThread(value);
        lowerThread.start();
        upperThread.start();
    }
}
