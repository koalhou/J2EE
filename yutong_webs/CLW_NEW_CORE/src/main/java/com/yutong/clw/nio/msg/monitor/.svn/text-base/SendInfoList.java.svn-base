package com.yutong.clw.nio.msg.monitor;

/**
 * <p>Copyright: Copyright (c) 2008</p>
 * @version 1.0
 */
import java.util.Hashtable;
import java.util.Vector;

public class SendInfoList {
        /** SaveUdp消息模块实例 */
        static private SendInfoList _instance;
        /** SaveUdp消息队列 */
        @SuppressWarnings("unchecked")
		private Hashtable list = null;
        /** 线程同步控制确保模块仅有一个实例 */
        static synchronized public SendInfoList getInstance() {
                if (_instance == null) {
                        _instance = new SendInfoList();
                }
                return _instance;
        }
        @SuppressWarnings("unchecked")
		public boolean regedit(String listName){
            if(listName==null) return false;
            if(list.containsKey(listName)){
                return true;
            }else{
                list.put(listName,new Vector(3000));
                return true;
            }
        }
        /** 构造器，默认消息队列长度为3000 */
        @SuppressWarnings("unchecked")
		private SendInfoList() {
                list=new Hashtable();
        }
        /** 向消息队列添加消息 */
        @SuppressWarnings("unchecked")
		public synchronized boolean add(SendBean msg,String listName){
            if(msg==null||listName==null) return false;
            if(list.containsKey(listName)){
                ((Vector)list.get(listName)).addElement(msg);
            }
            else
                return false;
            return true;
        }
        /** 向消息队列插入消息(添加到队列起始处) */
        @SuppressWarnings("unchecked")
		public synchronized boolean insert(SendBean msg,String listName){
            if(msg==null||listName==null) return false;
            if(list.containsKey(listName)){
                ((Vector)list.get(listName)).add(0,msg);
            }
            else
                return false;
            return true;
        }
        /** 返回并删除消息队列起始处消息，若消息队列为空，返回空 */
        @SuppressWarnings("unchecked")
		public synchronized SendBean remove(String listName){
            if(listName==null) return null;
            if(list.containsKey(listName)){
                if(((Vector)list.get(listName)).size()==0) return null;
                return  (SendBean) ((Vector)list.get(listName)).remove(0);
            }
            else
                return null;
        }
        /** 返回消息队列长度 */
        @SuppressWarnings("unchecked")
		public int getSize(String listName){
            if(listName==null) return -1;
            if(list.containsKey(listName)){
                return ((Vector)list.get(listName)).size();
            }
            else
                return -1;
        }
}