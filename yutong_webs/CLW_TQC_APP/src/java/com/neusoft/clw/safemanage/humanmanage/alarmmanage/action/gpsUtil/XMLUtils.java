package com.neusoft.clw.safemanage.humanmanage.alarmmanage.action.gpsUtil;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;


/**
 * <p>
 * Title: XML工具
 * </p>
 * 
 * <p>
 * Description: XML处理工具类
 * </p>
 * 
 */
public class XMLUtils {
	private static Logger log = Logger.getLogger(XMLUtils.class);

	public static final String XML_HEAD_GBK = "<?xml version=\"1.0\" encoding=\"GBK\"?>";
	public static final String XML_HEAD_GB2312 = "<?xml version=\"1.0\" encoding=\"GB2312\"?>";
	public static final String XML_HEAD_UTF8 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";

	public static final String ATTR_CLASS = "_Class"; // 标识JAVA类的属性名称
	public static final String ATTR_PARAM_TYPE = "_ParamType"; // 标识参数的JAVA类型
	public static final String ATTR_TYPE = "_AttrType"; // 标识类型的属性名称
	public static final String ATTR_LIST_TAG = "_AttrLTag"; // 标识List中各项所属用的Tag的属性名称
	public static final String TYPE_MAP = "map"; // 类型 Map
	public static final String TYPE_LIST = "list"; // 类型 List
	public static final String TYPE_PAIRLIST = "pairList"; // 类型 PairList
	public static final String DEF_LIST_TAG = "li"; // 默认 List 的各项的Tag

	public XMLUtils() {
	}

	/**
	 * 创建一个新的Document
	 * 
	 * @return Document
	 */
	public static Document buildDocument() {
		return DocumentHelper.createDocument();
	}

	/**
	 * 从XML字符串构建 dom4j document 对象
	 * 
	 * @param xmlString
	 *            String XML字符串
	 * @return Document
	 */
	public static Document buildDocFromXML(String xmlString) {
		try {
			return DocumentHelper.parseText(xmlString);
		} catch (DocumentException ex) {
			log.error("无法从XML字串构建dom4j Document:" + xmlString, ex);
			return null;
		}
	}

	/**
	 * 从文件构建dom4j document 对象
	 * 
	 * @param fileName
	 *            String 文件名(包含路径)
	 * @return Document
	 */
	public static Document buildDocFromFile(String fileName) {
		try {
			SAXReader sb = new SAXReader();
			return sb.read(new File(fileName));
		} catch (Exception ex) {
			log.error("无法从文件[" + fileName + "]构建dom4j document", ex);
			return null;
		}
	}

	/**
	 * 从输入流构建dom4j document对象
	 * 
	 * @param in
	 *            InputStream
	 * @return Document
	 */
	public static Document buildDocFromInputStream(InputStream in) {
		try {
			SAXReader sb = new SAXReader();
			return sb.read(in);
		} catch (Exception ex) {
			log.error("无法从输入流构建dom4j document", ex);
			return null;
		}
	}

	/**
	 * 从Reader流构建dom4j document对象
	 * 
	 * @param rd
	 *            Reader
	 * @return Document
	 */
	public static Document buildDocFromReader(Reader rd) {
		try {
			SAXReader sb = new SAXReader();
			return sb.read(rd);
		} catch (Exception ex) {
			log.error("无法从Reader构建dom4j document", ex);
			return null;
		}
	}

	/**
	 * 从URL流构建dom4j document对象
	 * 
	 * @param url
	 *            URL
	 * @return Document
	 */
	public static Document buildDocFromURL(URL url) {
		try {
			SAXReader sb = new SAXReader();
			return sb.read(url);
		} catch (Exception ex) {
			log.error("无法从URL[" + url + "]构建dom4j document", ex);
			return null;
		}
	}

	/**
	 * 构建由bean的所有get方法组织的结点,生成如下形式的xml:
	 * <node><name><get1>val1</get1><get2>val2</get2></name></node>
	 * 
	 * @deprecated
	 * @param node
	 *            Element 父结果
	 * @param name
	 *            String 结点名称
	 * @param obj
	 *            Object bean对象
	 */
	public static void addChildByBean(Element node, String name, Object obj) {
		if (node == null || obj == null)
			return;
		Element pe = node.addElement(name);
		Class clazz = obj.getClass();
		pe.addAttribute(ATTR_CLASS, clazz.getName());
		Method[] methods = clazz.getDeclaredMethods();
		Element e = null;
		Method md = null;
		String mname = null, aname = null, val = null;
		int iAttr = 0;
		for (int i = 0, l = methods.length; i < l; i++) {
			md = methods[i];
			mname = md.getName();
			clazz = md.getReturnType();
			if (mname.startsWith("get") && md.getParameterTypes().length == 0
					&& !clazz.equals(Void.TYPE)) {
				try {
					val = md.invoke(obj, null).toString();
					aname = mname.substring(3);
					e = pe.addElement(aname);
					e.addAttribute(ATTR_PARAM_TYPE, clazz.getName());
					e.setText(val);
					iAttr++;
				} catch (Exception ex) {
				}
			}
		}
		if (iAttr == 0)
			pe.setText(obj.toString());
	}

	/**
	 * 将List中的PairEntry对象插入Node中作为其子节点,形如：
	 * <Node><list[i].name>list[i].text</list[i].name></Node>
	 * 
	 * @param node
	 *            Element
	 * @param list
	 *            PairList
	 */
	public static void addChildByPairList(Element node, PairList list) {
		if (node == null || list == null || list.size() == 0)
			return;
		Element e = null;
		PairEntry pair = null;
		String name = null;
		for (int i = 0, l = list.size(); i < l; i++) {
			pair = list.get(i);
			name = pair.name;
			if (pair.name != null) {
				name = pair.name.trim();
				if (name.length() > 0) {
					e = node.addElement(pair.name);
					if (pair.value != null)
						e.setText(pair.value);
				}
			}
		}
	}

	public static void addChildByList(Element node, String name, List list) {
		addChildByList(node, name, list, true);
	}

	/**
	 * 将List中元素添加到Node中作为其子节点,形如:
	 * <Node><name>value1</name><name>value2</name></Node>
	 * 
	 * @param node
	 *            Element 父节点
	 * @param name
	 *            String 子节点的名称
	 * @param list
	 *            List 子节点的值列表
	 * @param bReloadable
	 *            boolean 是否可重载入（可重载入的XML会添加额外标记）
	 */
	public static void addChildByList(Element node, String name, List list,
			boolean bReloadable) {
		if (node == null || list == null || list.size() == 0)
			return;
		if (bReloadable) {
			node.addAttribute(ATTR_TYPE, TYPE_LIST);
			node.addAttribute(ATTR_LIST_TAG, name);
		}
		Element e = null;
		Object obj = null;
		for (int i = 0, l = list.size(); i < l; i++) {
			e = node.addElement(name);
			obj = list.get(i);
			if (obj instanceof Map) {
				addChildByMap(e, (Map) obj, bReloadable);
			} else if (obj instanceof List) {
				addChildByList(e, DEF_LIST_TAG, (List) obj, bReloadable);
			} else if (obj instanceof PairList) {
				addChildByPairList(e, (PairList) obj);
			} else if (obj != null) {
				e.setText(obj.toString());
			}
		}
	}

	public static void addChildByMap(Element node, Map map) {
		addChildByMap(node, map, true);
	}

	/**
	 * 将Map中的元素添加到Node中作为其子节点, 形如:
	 * <Node><key1>value1</key><key2>value2</key2>...</Node>
	 * 
	 * @param node
	 *            Element 父节点
	 * @param map
	 *            Map 子节点的名值对
	 * @param bReloadable
	 *            boolean 是否可重载入（可重载入的XML会添加额外标记）
	 */
	public static void addChildByMap(Element node, Map map, boolean bReloadable) {
		if (node == null || map == null || map.isEmpty())
			return;
		if (bReloadable)
			node.addAttribute(ATTR_TYPE, TYPE_MAP);
		Element e = null;
		Object obj = null;
		String name = null;
		Map.Entry et = null;
		Iterator it = map.entrySet().iterator();
		while (it.hasNext()) {
			et = ((Map.Entry) it.next());
			name = et.getKey().toString();
			e = node.addElement(name);
			obj = et.getValue();
			if (obj instanceof Map) {
				addChildByMap(e, (Map) obj, bReloadable);
			} else if (obj instanceof List) {
				addChildByList(e, DEF_LIST_TAG, (List) obj, bReloadable);
			} else if (obj instanceof PairList) {
				addChildByPairList(e, (PairList) obj);
			} else if (obj != null) {
				e.setText(obj.toString());
			}
		}
	}

	/**
	 * 获取指定节点下所有子节点的名字列表
	 * 
	 * @param node
	 *            Element
	 * @return List
	 */
	public static List getChildNodeNameList(Element node) {
		if (node == null)
			return null;
		List list = new ArrayList();
		Iterator it = node.elementIterator();
		while (it.hasNext()) {
			Element ele = (Element) it.next();
			list.add(ele.getName());
		}
		return list;
	}

	/**
	 * 获取指定节点下，所有子节点的值列表
	 * 
	 * @param node
	 *            Element
	 * @return List
	 */
	public static List getChildToList(Element node) {
		return getChildToList(node, null);
	}

	/**
	 * 获取指定节点下，指定名称的所有子节点的值列表
	 * 
	 * @param node
	 *            Element
	 * @param name
	 *            String 要求匹配的节点名称，如果为null匹配所有节点
	 * @return List
	 */
	public static List getChildToList(Element node, String name) {
		if (node == null)
			return null;
		List list = new ArrayList();
		String attrType = null;
		Iterator it = (name == null) ? node.elementIterator() : node
				.elementIterator(name);
		while (it.hasNext()) {
			Element ele = (Element) it.next();
			attrType = ele.attributeValue(ATTR_TYPE);
			if (TYPE_MAP.equals(attrType)) {
				list.add(getChildToMap(ele));
			} else if (TYPE_LIST.equals(attrType)) {
				list.add(getChildToList(ele, ele.attributeValue(ATTR_LIST_TAG)));
			} else if (TYPE_PAIRLIST.equals(attrType)) {
				list.add(getChildToPairList(ele));
			} else {
				list.add(ele.getText());
			}
		}
		return list;
	}

	/**
	 * 获取指定节点下，所有子节点的名值构成的PairList列表
	 * 
	 * @param node
	 *            Element
	 * @return List
	 */
	public static PairList getChildToPairList(Element node) {
		if (node == null)
			return null;
		PairList list = new PairList();
		Iterator it = node.elementIterator();
		while (it.hasNext()) {
			Element ele = (Element) it.next();
			list.add(new PairEntry(ele.getName(), ele.getText()));
		}
		return list;
	}

	/**
	 * 获取指定节点下的所有子节点名值构成的Map（递归生成Map）
	 * 
	 * @param node
	 *            Element
	 * @return Map
	 */
	public static Map getRecursiveMap(Element node) {
		if (node == null)
			return null;
		Map map = new LinkedHashMap();
		Element ele = null;
		Iterator it = node.elementIterator();
		while (it.hasNext()) {
			ele = (Element) it.next();
			if (ele.elements().size() > 0)
				map.put(ele.getName(), getRecursiveMap(ele));
			else
				map.put(ele.getName(), ele.getText());
		}
		return map;
	}

	public static List getRecursiveList(Element node) {
		return getRecursiveList(node, null);
	}

	/**
	 * 获取指定节点下的所有子节点名值构成的List（递归生成List）
	 * 
	 * @param node
	 *            Element
	 * @param name
	 *            String //为指定的节点名,为空为所有节点
	 * @return Map
	 */
	public static List getRecursiveList(Element node, String name) {
		if (node == null)
			return null;
		List list = new ArrayList();
		Element ele = null;
		Iterator it = (name == null) ? node.elementIterator() : node
				.elementIterator(name);
		while (it.hasNext()) {
			ele = (Element) it.next();
			if (ele.elements().size() > 0)
				list.add(getRecursiveList(ele, name));
			else
				list.add(ele.getText());
		}
		return list;
	}

	/**
	 * 获取指定节点下，所有子节点的名值构成的Map
	 * 
	 * @param node
	 *            Element
	 * @return Map
	 */
	public static Map getChildToMap(Element node) {
		if (node == null)
			return null;
		Map map = new LinkedHashMap();
		Element ele = null;
		String attrType = null;
		Iterator it = node.elementIterator();
		while (it.hasNext()) {
			ele = (Element) it.next();
			attrType = ele.attributeValue(ATTR_TYPE);
			if (TYPE_MAP.equals(attrType)) {
				map.put(ele.getName(), getChildToMap(ele));
			} else if (TYPE_LIST.equals(attrType)) {
				map.put(ele.getName(),
						getChildToList(ele, ele.attributeValue(ATTR_LIST_TAG)));
			} else if (TYPE_PAIRLIST.equals(attrType)) {
				map.put(ele.getName(), getChildToPairList(ele));
			} else {
				map.put(ele.getName(), ele.getText());
			}
		}
		return map;
	}

	/**
	 * 获取指定编码的XML
	 * 
	 * @param doc
	 *            Document
	 * @param encoding
	 *            String
	 * @return String
	 */
	public static String toXMLString(Document doc, String encoding) {
		try {
			StringWriter out = new StringWriter();
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding(encoding);
			format.setLineSeparator("\r\n");
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(doc);
			writer.close();
			return out.toString();
		} catch (Exception ex) {
			log.error("发生错误,无法输出XML[" + encoding + "]", ex);
			return "";
		}
	}

	/**
	 * 采用默认编码(GB2312)输出XML到OutputStream中
	 * 
	 * @param doc
	 *            Document
	 * @param out
	 *            OutputStream
	 * @return boolean
	 */
	public static boolean writeXML(Document doc, OutputStream out) {
		return writeXML(doc, out, "GB2312");
	}

	/**
	 * 输出XML到OutputStream中
	 * 
	 * @param doc
	 *            Document
	 * @param out
	 *            OutputStream
	 * @param encoding
	 *            String
	 * @return boolean
	 */
	public static boolean writeXML(Document doc, OutputStream out,
			String encoding) {
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding(encoding);
			format.setLineSeparator("\r\n");
			XMLWriter writer = new XMLWriter(out, format);
			writer.write(doc);
			writer.close();
			return true;
		} catch (Exception ex) {
			log.error("发生错误,无法输出XML[" + encoding + "]到输出流中", ex);
			return false;
		}
	}

	/**
	 * 采用默认编码(GB2312)输出XML到Writer中
	 * 
	 * @param doc
	 *            Document
	 * @param wr
	 *            Writer
	 * @return boolean
	 */
	public static boolean writeXML(Document doc, Writer wr) {
		return writeXML(doc, wr, "GB2312");
	}

	/**
	 * 输出XML到Writer中
	 * 
	 * @param doc
	 *            Document
	 * @param wr
	 *            Writer
	 * @param encoding
	 *            String 编码
	 * @return boolean
	 */
	public static boolean writeXML(Document doc, Writer wr, String encoding) {
		try {
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding(encoding);
			format.setLineSeparator("\r\n");
			XMLWriter writer = new XMLWriter(wr, format);
			writer.write(doc);
			writer.close();
			return true;
		} catch (Exception ex) {
			log.error("发生错误,无法输出XML[" + encoding + "]到输出流中", ex);
			return false;
		}
	}

	/**
	 * 采用默认编码(GB2312)输出XML到文件中
	 * 
	 * @param doc
	 *            Document
	 * @param fileName
	 *            String 文件名
	 * @return boolean
	 */
	public static boolean writeXML(Document doc, String fileName) {
		return writeXML(doc, fileName, "GB2312");
	}

	/**
	 * 输出XML到文件中
	 * 
	 * @param doc
	 *            Document
	 * @param fileName
	 *            String 文件名
	 * @param encoding
	 *            String 编码
	 * @return boolean
	 */
	public static boolean writeXML(Document doc, String fileName,
			String encoding) {
		try {
			FileWriter fw = new FileWriter(fileName);
			OutputFormat format = OutputFormat.createPrettyPrint();
			format.setEncoding(encoding);
			format.setLineSeparator("\r\n");
			XMLWriter writer = new XMLWriter(fw, format);
			writer.write(doc);
			writer.close();
			return true;
		} catch (Exception ex) {
			log.error("发生错误,无法输出XML[" + encoding + "]到文件:" + fileName, ex);
			return false;
		}
	}

	// ==== test ==============================
	public static void main(String[] args) {
		Document doc = XMLUtils.buildDocument();
		Element ele = doc.addElement("Root");
		addChildByBean(ele, "BEAN", new Integer(10));
		System.out.println("=====================");
		System.out.println(XMLUtils.toXMLString(doc, "GB2312"));
	}
}
