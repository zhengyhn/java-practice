package io.github.zhengyhn.practice.my_array_blocking_queue;

import java.util.Objects;

public final class MyArrayBlockingQueue<T> {
    private Integer size;

    private Integer count = 0;

    private Integer putIndex = 0;

    private Integer getIndex = 0;

    private Object[] items;

    private Object fullLock = new Object();

    private Object emptyLock = new Object();

    public MyArrayBlockingQueue(int size) {
        this.size = size;
        items = new Object[size];
    }

    public void put(T item) {
        synchronized (this.fullLock) {
            while (Objects.equals(this.count, this.size)) {
                try {
                    this.fullLock.wait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        synchronized (this.emptyLock) {
            items[this.putIndex] = item;
            ++this.putIndex;
            ++this.count;
            if (Objects.equals(this.count, this.size)) {
                this.putIndex = 0;
            }
            this.emptyLock.notify();
        }
    }

    public T take() {
        synchronized (this.emptyLock) {
            while (Objects.equals(this.count, 0)) {
                try {
                    this.emptyLock.wait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        Object val = null;
        synchronized (this.fullLock) {
            val = this.items[this.getIndex];
            this.items[this.getIndex] = null;
            --this.count;
            ++this.getIndex;
            if (Objects.equals(this.getIndex, this.size)) {
                this.getIndex = 0;
            }
            this.fullLock.notify();
        }
        return (T)val;
    }

    public Boolean empty() {
        return this.count == 0;
    }

    public Integer size() {
        return this.count;
    }
}
