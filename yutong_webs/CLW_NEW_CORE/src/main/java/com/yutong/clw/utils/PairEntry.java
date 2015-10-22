package com.yutong.clw.utils;

/**
 * <p>
 * Title: XML工具
 * </p>
 * 
 * <p>
 * Description: 名值对实体对象
 * </p>
 * 
 * 
 */
public class PairEntry {
	public String name = null;
	public String value = null;

	public PairEntry() {
	}

	public PairEntry(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}

	public String toString() {
		return name + "=" + value;
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj instanceof PairEntry) {
			PairEntry pe = (PairEntry) obj;
			return name.toString().equals(pe.toString());
		} else
			return false;
	}
}
