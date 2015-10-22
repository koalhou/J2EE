package com.yutong.clw.quartz.report;

public class MyCountDown {
	private  int count;
	private static MyCountDown instance_ = null;
	public MyCountDown(int count) {
		this.count = count;
	}
	public static MyCountDown instance() {
        if (instance_ == null) instance_ = new MyCountDown(0);
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}
