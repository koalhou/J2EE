package com.neusoft.SchoolBus.vncs.thread;

public class QuartzCountDown {
	private  int count;
	private static QuartzCountDown instance_ = null;
	public QuartzCountDown(int count) {
		this.count = count;
	}
	public static QuartzCountDown instance() {
        if (instance_ == null) instance_ = new QuartzCountDown(0);
        return instance_;
    }
	public synchronized void countDown() {
		count--;
	}
	public synchronized void countAdd() {
		count++;
	}
	public synchronized boolean hasNext() {
		return (count > 0);
	}

	public synchronized int  getCount() {
		return count;
	}

	public synchronized void  setCount(int count) {
		this.count = count;
	}
}
