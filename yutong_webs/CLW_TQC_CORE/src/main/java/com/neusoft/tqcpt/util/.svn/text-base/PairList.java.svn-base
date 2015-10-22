package com.neusoft.tqcpt.util;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;


/**
 * <p>
 * Title: XML工具
 * </p>
 * 
 * <p>
 * Description: PairEntry专用的List
 * </p>
 * 
 */
public class PairList {
	private List list = new LinkedList();

	public PairList() {
	}

	public boolean add(PairEntry o) {
		return list.add(o);
	}

	public boolean add(String name, String value) {
		return list.add(new PairEntry(name, value));
	}

	public void add(int index, String name, String value) {
		list.add(index, new PairEntry(name, value));
	}

	public void add(int index, PairEntry element) {
		list.add(index, element);
	}

	public void addAll(Collection c) {
		addAll(list.size(), c);
	}

	public void addAll(int index, Collection c) {
		if (c != null) {
			Iterator it = c.iterator();
			Object obj;
			while (it.hasNext()) {
				obj = it.next();
				if (obj instanceof PairEntry) {
					list.add(index++, obj);
				}
			}
		}
	}

	public boolean addAll(int index, PairList c) {
		if (c != null)
			return list.addAll(index, c.getList());
		return false;
	}

	public boolean addAll(PairList c) {
		if (c != null)
			return list.addAll(c.getList());
		return false;
	}

	public void clear() {
		list.clear();
	}

	public boolean contains(PairEntry o) {
		return list.contains(o);
	}

	public boolean containsAll(PairList c) {
		return list.containsAll(c.getList());
	}

	public PairEntry get(int index) {
		return (PairEntry) list.get(index);
	}

	public int indexOf(PairEntry o) {
		return list.indexOf(o);
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public Iterator iterator() {
		return list.iterator();
	}

	public int lastIndexOf(PairEntry o) {
		return list.lastIndexOf(o);
	}

	public ListIterator listIterator(int index) {
		return list.listIterator(index);
	}

	public ListIterator listIterator() {
		return list.listIterator();
	}

	public PairEntry remove(int index) {
		return (PairEntry) list.remove(index);
	}

	public boolean remove(PairEntry o) {
		return list.remove(o);
	}

	public boolean removeAll(PairList c) {
		return list.remove(c.getList());
	}

	public boolean retainAll(PairList c) {
		return list.retainAll(c.getList());
	}

	public Object set(int index, PairList element) {
		return list.set(index, element);
	}

	public int size() {
		return list.size();
	}

	public PairList subList(int fromIndex, int toIndex) {
		List lt = list.subList(fromIndex, toIndex);
		PairList pl = new PairList();
		if (lt != null && lt.size() > 0) {
			pl.addAll(lt);
		}
		return pl;
	}

	public PairEntry[] toArray() {
		return (PairEntry[]) list.toArray();
	}

	/**
	 * 获取只读的 List (java.util.List)
	 * 
	 * @return List
	 */
	public List getList() {
		return Collections.unmodifiableList(list);
	}
}
