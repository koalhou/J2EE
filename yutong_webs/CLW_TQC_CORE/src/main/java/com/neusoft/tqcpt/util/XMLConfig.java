package com.neusoft.tqcpt.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;


/**
 * <p>
 * Title: XML工具
 * </p>
 * 
 * <p>
 * Description: XML配置文件工具类
 * </p>
 * 
 */
public class XMLConfig {
	private static Logger log = Logger.getLogger(XMLConfig.class);

	private String configFile = null;
	private Document doc = null;

	public XMLConfig() {
	}

	public XMLConfig(String fileName) {
		setConfigFile(fileName);
	}

	/**
	 * 是否已初始化
	 * 
	 * @return boolean
	 */
	public boolean isInit() {
		return (doc != null);
	}

	/**
	 * 创建一个默认的配置document
	 * 
	 * @return boolean
	 */
	public void initConfig() {
		doc = XMLUtils.buildDocument();
	}

	/**
	 * 从默认设置的XML文件载入配置信息
	 * 
	 * @return boolean
	 */
	public boolean loadConfig() {
		if (configFile == null)
			return false;
		else
			return loadConfigByFileName(configFile);
	}

	/**
	 * 从XML文件载入配置
	 * 
	 * @param fileName
	 *            String
	 * @return boolean
	 */
	public boolean loadConfigByFileName(String fileName) {
		try {
			setConfigFile(fileName);
			doc = XMLUtils
					.buildDocFromInputStream(getResourceAsStream(fileName));
			return (doc != null);
		} catch (Exception ex) {
			doc = null;
			log.error("无法载入配置文件:" + fileName, ex);
			return false;
		}
	}
	
	
	/**
	 * 从XML字符串载入配置
	 * 
	 * @param fileName
	 *            String
	 * @return boolean
	 */
	public boolean loadConfigByXmlString(String xmlString) {
		try {
			doc = XMLUtils.buildDocFromXML(xmlString);
			return (doc != null);
		} catch (Exception ex) {
			doc = null;
			log.error("无法载入配置文件:" + xmlString, ex);
			return false;
		}
	}
	

	/**
	 * 重新载入配置文件(只支持已从loadConfig(fileName)文件读取过配置的情况)
	 * 
	 * @return boolean
	 */
	public boolean reloadConfig() {
		return loadConfig();
	}

	/**
	 * 保存配置信息到默认设置的XML文件
	 * 
	 * @return boolean
	 */
	public boolean saveConfig() {
		return saveConfig(configFile);
	}

	/**
	 * 保存配置信息到指定的XML文件
	 * 
	 * @param fileName
	 *            String
	 * @return boolean
	 */
	public boolean saveConfig(String fileName) {
		URL url = XMLConfig.class.getClassLoader().getResource(fileName);
		boolean ret = XMLUtils.writeXML(doc, url.getFile());
		if (!ret)
			ret = XMLUtils.writeXML(doc, fileName);
		return ret;
	}

	/**
	 * 设置配置文件名
	 * 
	 * @param fileName
	 *            String
	 */
	public void setConfigFile(String fileName) {
		this.configFile = fileName;
	}

	/**
	 * 获取配置文件名
	 * 
	 * @return String
	 */
	public String getConfigFile() {
		return this.configFile;
	}

	/**
	 * 从Document对象载入配置
	 * 
	 * @param doc
	 *            Document
	 * @return boolean
	 */
	public boolean setConfigDocument(Document doc) {
		this.doc = doc;
		return (this.doc != null);
	}

	/**
	 * 获取配置的Document对象
	 * 
	 * @return Document
	 */
	public Document getConfigDocment() {
		return this.doc;
	}

	/**
	 * 获取指定路径结点的指定属性值, 节点不存在或属性不存在时返回null;
	 * 
	 * @param path
	 *            String
	 * @param attrName
	 *            String
	 * @return String
	 */
	public String getAttribute(String path, String attrName) {
		return getAttribute(path, attrName, null);
	}

	/**
	 * 获取指定路径结点的指定属性值, 节点不存在或属性不存在时返回默认值
	 * 
	 * @param path
	 *            String 节点路径
	 * @param attrName
	 *            String 属性名称
	 * @param defValue
	 *            String 默认值
	 */
	public String getAttribute(String path, String attrName, String defValue) {
		try {
			Object obj = doc.selectSingleNode(path);
			if (obj != null && obj instanceof Element) {
				Element ele = (Element) obj;
				Attribute attr = ele.attribute(attrName);
				return attr == null ? defValue : attr.getValue();
			} else {
				return defValue;
			}
		} catch (Exception ex) {
			return defValue;
		}
	}

	/**
	 * 设置指定路径结点的指定属性值
	 * 
	 * @param path
	 *            String 节点路径
	 * @param attrName
	 *            String 属性名
	 * @param value
	 *            String 属性值
	 */
	public void setAttribute(String path, String attrName, String value) {
		Node node = findOrCreateNode(path);
		if (node != null && node instanceof Element) {
			((Element) node).addAttribute(attrName, value);
		}
	}

	/**
	 * 得到配置属性的字符串值
	 * 
	 * @param path
	 *            配置属性的xpath
	 * @return 配置属性的值，如果配置属性没有设置值，则返回null
	 */
	public String getString(String path) {
		return getString(path, null);
	}

	/**
	 * 得到配置属性的值
	 * 
	 * @param path
	 *            配置属性的xpath
	 * @param defValue
	 *            默认值
	 * @return 配置属性的值
	 */
	public String getString(String path, String defValue) {
		try {
			Object obj = doc.selectSingleNode(path);
			if (obj != null && obj instanceof Element) {
				Element ele = (Element) obj;
				return ele.getText();
			} else {
				return defValue;
			}
		} catch (Exception ex) {
			return defValue;
		}
	}

	/**
	 * 设置指定路径节点的值（如果不存在则创建）
	 * 
	 * @param path
	 *            String 用"/"分隔的简单路径,如：/config/system/version
	 * @param value
	 *            String
	 */
	public void setString(String path, String value) {
		Node node = findOrCreateNode(path);
		if (node != null) {
			node.setText(value);
		}
	}

	/**
	 * 获取指定XPath下所有子节点构成的字符串数组
	 * 
	 * @param path
	 *            String
	 * @return String[]
	 */
	public String[] getStrings(String path) {
		return getStrings(path, null);
	}

	/**
	 * 获取指定XPath下所有子节点构成的字符串数组
	 * 
	 * @param path
	 *            String
	 * @param defValue
	 *            String[]
	 * @return String[]
	 */
	public String[] getStrings(String path, String[] defValue) {
		try {
			Object obj = doc.selectSingleNode(path);
			if (obj != null && obj instanceof Element) {
				Element ele = (Element) obj;
				List list = new ArrayList();
				Iterator it = ele.elementIterator();
				while (it.hasNext()) {
					Element e = (Element) it.next();
					list.add(e.getText());
				}
				String[] tmp = new String[list.size()];
				list.toArray(tmp);
				return tmp;
			} else {
				return defValue;
			}
		} catch (Exception ex) {
			return defValue;
		}
	}

	/**
	 * 在指定XPath下构建指定names中各项值的多个子节点, 并设置相应的值
	 * 
	 * @param path
	 *            String
	 * @param names
	 *            String[] 名称列表
	 * @param values
	 *            String[] 值列表
	 */
	public void setStrings(String path, String[] names, String[] values) {
		Node node = findOrCreateNode(path);
		if (node != null && node instanceof Element) {
			Element ele = (Element) node;
			String value = "";
			for (int i = 0; i < names.length; i++) {
				value = (i < values.length ? values[i] : "");
				ele.addElement(names[i]).setText(value);
			}
		}
	}

	/**
	 * 在指定XPath下构建指定names中各项值的多个子节点, 并设置相应的值
	 * 
	 * @param path
	 *            String
	 * @param nameList
	 *            List
	 * @param values
	 *            String[]
	 */
	public void setStrings(String path, List nameList, String[] values) {
		String[] names = new String[nameList.size()];
		nameList.toArray(names);
		setStrings(path, names, values);
	}

	/**
	 * 在指定XPath下构建指定字符串数组值的多个子节点
	 * 
	 * @param path
	 *            String
	 * @param name
	 *            String
	 * @param values
	 *            String[]
	 */
	public void setStrings(String path, String name, String[] values) {
		Node node = findOrCreateNode(path);
		if (node != null && node instanceof Element) {
			Element ele = (Element) node;
			for (int i = 0; i < values.length; i++) {
				ele.addElement(name).setText(values[i]);
			}
		}
	}

	/**
	 * 获取指定xpath下的子节点构成的 XmlNodePair 列表
	 * 
	 * @param path
	 *            String
	 * @return List
	 */
	public PairList getPairList(String path) {
		try {
			Object obj = doc.selectSingleNode(path);
			if (obj != null && obj instanceof Element) {
				return XMLUtils.getChildToPairList((Element) obj);
			} else {
				return null;
			}
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 在指定XPath下构建指定PairList中各项值的多个子节点
	 * 
	 * @param path
	 *            String
	 * @param pairList
	 *            PairList
	 */
	public void setPairList(String path, PairList pairList) {
		Node node = findOrCreateNode(path);
		if (node != null && node instanceof Element) {
			Element ele = (Element) node;
			for (int i = 0; i < pairList.size(); i++) {
				PairEntry entry = pairList.get(i);
				ele.addElement(entry.name).setText(entry.value);
			}
		}
	}

	/**
	 * 根据xpath获取一个节点下面的多个相同节点名的数据
	 * @param path
	 * @return
	 */
	public List<Map> getMapList(String path) {
		try {
			List<Node> listObj = doc.selectNodes(path);
			List<Map> arrayList = new ArrayList();
			for (int i = 0; i < listObj.size(); i++) {
				if (listObj.get(i) != null && listObj.get(i) instanceof Element) {
					Map map = XMLUtils
							.getRecursiveMap((Element) listObj.get(i));
					arrayList.add(map);
				} else {
					return null;
				}
			}
			return arrayList;
		} catch (Exception ex) {
			return null;
		}
	}
	
	/**
	 * 获取指定xpath下的子节点构成的值列表
	 * 
	 * @param path
	 *            String
	 * @return List
	 */
	public List getValueList(String path) {
		return getValueList(path, null);
	}

	/**
	 * 获取指定xpath下的子节点中指定名称节点构成的值列表
	 * 
	 * @param path
	 *            String
	 * @param name
	 *            Stirng 为空则匹配所有节点
	 * @return List
	 */
	public List getValueList(String path, String name) {
		try {
			Object obj = doc.selectSingleNode(path);
			if (obj != null && obj instanceof Element) {
				return XMLUtils.getChildToList((Element) obj, name);
			} else {
				return null;
			}
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 在指定XPath下构建指定names中各项值的多个子节点, 并设置相应值
	 * 
	 * @param path
	 *            String
	 * @param names
	 *            String[] 名称列表
	 * @param list
	 *            List 值列表
	 */
	public void setValueList(String path, String[] names, List list) {
		Node node = findOrCreateNode(path);
		if (node != null && node instanceof Element) {
			Element ele = (Element) node;
			Object obj = null;
			String value = "";
			for (int i = 0; i < names.length; i++) {
				obj = (i < list.size() ? list.get(i) : null);
				value = (obj == null ? "" : obj.toString());
				ele.addElement(names[i]).setText(value);
			}
		}
	}

	/**
	 * 在指定XPath下构建指定names中各项值的多个子节点, 并设置相应值
	 * 
	 * @param path
	 *            String
	 * @param nameList
	 *            List
	 * @param valueList
	 *            List
	 */
	public void setValueList(String path, List nameList, List valueList) {
		String[] names = new String[nameList.size()];
		nameList.toArray(names);
		setValueList(path, names, valueList);
	}

	/**
	 * 在指定XPath下构建指定list中各项值的多个子节点
	 * 
	 * @param path
	 *            String
	 * @param name
	 *            String 节点名称
	 * @param list
	 *            List 值列表
	 */
	public void setValueList(String path, String name, List list) {
		Node node = findOrCreateNode(path);
		if (node != null && node instanceof Element) {
			Element ele = (Element) node;
			Object obj = null;
			String value = "";
			for (int i = 0; i < list.size(); i++) {
				obj = list.get(i);
				value = (obj == null ? "" : obj.toString());
				ele.addElement(name).setText(value);
			}
		}
	}

	/**
	 * 获取指定xpath下的子节点构成的Map
	 * 
	 * @param path
	 *            String
	 * @return List
	 */
	public Map getValueMap(String path) {
		try {
			Object obj = doc.selectSingleNode(path);
			if (obj != null && obj instanceof Element) {
				return XMLUtils.getRecursiveMap((Element) obj);
			} else {
				return null;
			}
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 在指定XPath下构建指定Map中各项名值对构成的多个子节点
	 * 
	 * @param path
	 *            String
	 * @param map
	 *            Map
	 */
	public void setValueMap(String path, Map map) {
		Node node = findOrCreateNode(path);
		if (node != null && node instanceof Element) {
			Element ele = (Element) node;
			Map.Entry entry = null;
			Object keyobj = null, valobj = null;
			String key = "", value = "";
			Iterator it = map.entrySet().iterator();
			while (it.hasNext()) {
				entry = (Map.Entry) it.next();
				keyobj = entry.getKey();
				if (keyobj != null) {
					key = keyobj.toString().trim();
					if (key.length() > 0) {
						valobj = entry.getValue();
						value = (valobj == null ? "" : valobj.toString());
						ele.addElement(key).setText(value);
					}
				}
			}
		}
	}

	/**
	 * 获取指定节点下的所有子节点列表
	 * 
	 * @param path
	 *            String
	 * @return List
	 */
	public List getSubKeyList(String path) {
		try {
			Object obj = doc.selectSingleNode(path);
			if (obj != null && obj instanceof Element) {
				return XMLUtils.getChildNodeNameList((Element) obj);
			} else {
				return null;
			}
		} catch (Exception ex) {
			return null;
		}
	}

	/**
	 * 获取配置属性的int型值
	 * 
	 * @param path
	 *            String
	 * @param defValue
	 *            int
	 * @return int
	 */
	public int getInt(String path, int defValue) {
		try {
			String val = getString(path, null);
			return Integer.parseInt(val);
		} catch (Exception ex) {
			return defValue;
		}
	}

	/**
	 * 获取配置属性的long型值
	 * 
	 * @param path
	 *            String
	 * @param defValue
	 *            long
	 * @return long
	 */
	public long getLong(String path, long defValue) {
		try {
			String val = getString(path, null);
			return Long.parseLong(val);
		} catch (Exception ex) {
			return defValue;
		}
	}

	/**
	 * 获取配置属性的float型值
	 * 
	 * @param path
	 *            String
	 * @param defValue
	 *            float
	 * @return float
	 */
	public float getFloat(String path, float defValue) {
		try {
			String val = getString(path, null);
			return Float.parseFloat(val);
		} catch (Exception ex) {
			return defValue;
		}
	}

	/**
	 * 获取配置属性的double型值
	 * 
	 * @param path
	 *            String
	 * @param defValue
	 *            double
	 * @return double
	 */
	public double getDouble(String path, double defValue) {
		try {
			String val = getString(path, null);
			return Double.parseDouble(val);
		} catch (Exception ex) {
			return defValue;
		}
	}

	/**
	 * 获取配置属性的boolean型值
	 * 
	 * @param path
	 *            String
	 * @param defValue
	 *            boolean
	 * @return boolean
	 */
	public boolean getBoolean(String path, boolean defValue) {
		String text = getString(path, null);
		if (text == null)
			return defValue;
		else
			return (("true".equalsIgnoreCase(text))
					|| ("yes".equalsIgnoreCase(text))
					|| ("enable".equalsIgnoreCase(text))
					|| ("on".equalsIgnoreCase(text)) || ("1"
					.equalsIgnoreCase(text)));
	}

	/**
	 * 从资源文件中得到文件的输入流
	 * 
	 * @param resource
	 *            资源文件的名称，这个文件应该存在classpath下
	 * @return 文件的输入流
	 * @throws IOException
	 *             如果读取文件错误，则抛出该异常。
	 */
	public InputStream getResourceAsStream(String resource) throws Exception {
		InputStream in = XMLConfig.class.getClassLoader().getResourceAsStream(
				resource);
		if (in == null) {
			in = ClassLoader.getSystemResourceAsStream(resource);
		}
		if (in == null) {
			in = new FileInputStream(resource);
		}
		if (in == null)
			throw new IOException("无法读取资源文件: " + resource);
		return in;
	}

	public String toXMLString() {
		return XMLUtils.toXMLString(doc, "GB2312");
	}

	public String toXMLString(String encoding) {
		return XMLUtils.toXMLString(doc, encoding);
	}

	/**
	 * 查找指定路径的结点，不存在时构建它
	 * 
	 * @param path
	 *            String
	 * @return Node
	 */
	private Node findOrCreateNode(String path) {
		Node node = null;
		try {
			doc.selectSingleNode(path);
		} catch (Exception ex) {
		}
		if (node == null)
			node = DocumentHelper.makeElement(doc, path);
		return node;
	}

	// =========== test ====================================
	public static void main(String[] args) {
		XMLConfig cfg = new XMLConfig();
		if (cfg.loadConfigByFileName("d:/config.xml")) {
			List<Map> list = (cfg.getMapList("/spatial_response/SpatialBean/roadList/Road"));
				System.out.println(list.get(0));
				System.out.println(list.get(1));
				
		}
//		System.out.println(cfg.getAttribute("/config/system/version", "type"));
//		String path = "/cfg/system/test/lucas/zheng";
//		cfg.initConfig();
//		cfg.setString(path, "" + System.currentTimeMillis());
//		cfg.setStrings(path, "tt", new String[] { "1", "2", "3" });
//		cfg.setStrings(path, new String[] { "AA", "BB", "CC", "DD", "EE" },
//				new String[] { "A", "B", "C" });
//		cfg.setStrings(path, new String[] { "I", "II" }, new String[] { "I",
//				"II", "III" });
//		List list = new ArrayList();
//		for (int i = 0; i < 10; i++) {
//			list.add("li_" + i);
//		}
//		cfg.setStrings(path, list, new String[] { "L_I", "L_II", "L_III" });
//		Map map = new HashMap();
//		map.put(null, "1");
//		map.put("", "2");
//		map.put("3", null);
//		map.put("4", "");
//		map.put("5", "m5");
//		cfg.setValueMap(path, map);
//
//		cfg.saveConfig();
	}
}
