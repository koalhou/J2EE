package com.neusoft.clw.util.mfqueue;

import java.util.Vector;

@SuppressWarnings({ "unchecked", "serial" })
public class Queue extends Vector implements IQueue {
    public Queue() {
    }

    public synchronized void put(Object obj) {
        super.add(obj);
    }

    public synchronized Object get() {
        if (super.isEmpty()) {
            return null;
        }
        Object obj = super.get(0);
        super.remove(0);
        return obj;
    }

    public synchronized int size() {
        return super.size();
    }
}
