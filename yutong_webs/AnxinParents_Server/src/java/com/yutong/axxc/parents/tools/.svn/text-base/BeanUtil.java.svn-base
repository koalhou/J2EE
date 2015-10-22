package com.yutong.axxc.parents.tools;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.reflect.MethodUtils;
import org.springframework.stereotype.Component;

public class BeanUtil {

	/**
	 * 一次获取num个UUID
	 * 
	 * @param num
	 *            数量
	 * @return
	 */
	public static String[] getUUIDs(int num) {
		if (num < 1) {
			return null;
		}
		String[] ss = new String[num];
		for (int i = 0; i < num; i++) {
			ss[i] = UUID.randomUUID().toString();
		}
		return ss;
	}

	/**
	 * 按格式转换时间到DATE
	 * 
	 * @param time
	 *            需要转换的时间串
	 * @param format
	 *            格式
	 * @return
	 * @throws ParseException
	 *             格式转换异常
	 */
	public static Date checkTimeForm(String time, String format)
			throws ParseException {
		return new SimpleDateFormat(format).parse(time);
	}

	/**
	 * 验证
	 * 
	 * @param obj
	 * @throws IllegalArgumentException
	 * @throws SecurityException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws BeanUtilException
	 */
	public static void checkObjectLegal(Object target)
			throws BeanUtilException, IllegalAccessException,
			NoSuchMethodException, InvocationTargetException {
		if (target == null) {
			throw new BeanUtilException("Target is NULL");
		}
		CheckBean c = target.getClass().getAnnotation(CheckBean.class);// TODO
		if (c == null) {
			checkUnknowClassLegal(target);
		} else {
			checkBeanLegal(target);
		}
	}

	/**
	 * @param target
	 * @return
	 * @throws BeanUtilException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 */
	public static void checkUnknowClassLegal(Object target)
			throws BeanUtilException, IllegalAccessException,
			NoSuchMethodException, InvocationTargetException {
		if (String.class.isAssignableFrom(target.getClass())) {
			checkStringNull((String) target);
		} else if (Collection.class.isAssignableFrom(target.getClass())) {
			checkCollectionNull(target);
		} else if (Map.class.isAssignableFrom(target.getClass())) {
			checkMapNull(target);
		} else if (Integer.class.isAssignableFrom(target.getClass())
				|| Byte.class.isAssignableFrom(target.getClass())
				|| Boolean.class.isAssignableFrom(target.getClass())
				|| Long.class.isAssignableFrom(target.getClass())
				|| Double.class.isAssignableFrom(target.getClass())
				|| Character.class.isAssignableFrom(target.getClass())
				|| Short.class.isAssignableFrom(target.getClass())
				|| Float.class.isAssignableFrom(target.getClass())
				|| Number.class.isAssignableFrom(target.getClass())) {
			return;
		} else {
			throw new BeanUtilException("不支持类型" + target.getClass().getName());
		}
	}

	/**
	 * @param target
	 * @throws BeanUtilException
	 */
	private static void checkStringNull(String target) throws BeanUtilException {
		target = StringUtils.strip(target);
		if (StringUtils.isBlank(target)) {
			throw new BeanUtilException("参数非法");
		}
	}

	/**
	 * @param target
	 * @throws BeanUtilException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 */
	public static void checkMapNull(Object target) throws BeanUtilException,
			IllegalAccessException, NoSuchMethodException,
			InvocationTargetException {
		Map<?, ?> o = (Map<?, ?>) target;
		if (o.size() == 0) {
			throw new BeanUtilException("MAP参数长度为0");
		}
		Set<?> s = o.entrySet();
		Iterator<?> it = s.iterator();
		while (it.hasNext()) {
			Map.Entry<?, ?> e = (Map.Entry<?, ?>) it.next();
			if (String.class.isAssignableFrom(e.getValue().getClass())) {
				checkStringNull((String) e.getValue());
			} else {
				checkObjectLegal(e.getValue());
			}
		}
	}

	/**
	 * @param target
	 * @throws BeanUtilException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 */
	public static void checkCollectionNull(Object target)
			throws BeanUtilException, IllegalAccessException,
			NoSuchMethodException, InvocationTargetException {
		Collection<?> o = (Collection<?>) target;
		if (o.size() == 0) {
			throw new BeanUtilException("参数长度为0");
		}
		Iterator<?> it = o.iterator();
		while (it.hasNext()) {
			checkObjectLegal(it.next());
		}
	}

	/**
	 * @param target
	 * @throws IllegalAccessException
	 * @throws BeanUtilException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	public static void checkBeanLegal(Object target) throws BeanUtilException,
			IllegalAccessException, NoSuchMethodException,
			InvocationTargetException {
		Class<?> clz = target.getClass();
		Field[] fs = clz.getDeclaredFields();
		for (Field f : fs) {
			Require r = f.getAnnotation(Require.class); // TODO 优化
			if (r == null) {
				continue;
			} else {
				fieldLegal(f, target, r);
			}
		}
	}

	/**
	 * 与Require标注相配合，检查Field实例的属性是否为空
	 * 
	 * @param f
	 * @param r
	 * @param obj
	 * @param clz
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws SecurityException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 * @throws BeanUtilException
	 */
	private static void fieldLegal(Field f, Object target, Require r)
			throws BeanUtilException, IllegalAccessException,
			NoSuchMethodException, InvocationTargetException {
		// 验证是否为支持的类型
		if (r.contentType() == Require.AttributeType.TEXT) {
			checkTextLegal(f, target, r);
		} else if (r.contentType() == Require.AttributeType.TIME) {
			checkTimeLegal(f, target, r);
		} else {
			throw new BeanUtilException("不支持检查type[" + r.contentType() + "]类型");
		}
	}

	/**
	 * 验证内容为时间格式的
	 * 
	 * @param o
	 * @param r
	 * @return
	 * @throws IllegalAccessException
	 * @throws BeanUtilException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private static void checkTimeLegal(Field f, Object target, Require r)
			throws IllegalAccessException, BeanUtilException,
			NoSuchMethodException, InvocationTargetException {
		String src = checkStringNull(f, target, r);
		try {
			TimeUtil.parseStringToDate(src, r.timeForm());
		} catch (ParseException e) {
			throw new BeanUtilException("参数[" + target.getClass() + ":"
					+ (StringUtils.isEmpty(r.name()) ? f.getName() : r.name())
					+ "]不符合要求", e);
		}
	}

	/**
	 * 验证内容为文本格式的
	 * 
	 * @param o
	 * @return true - 合法 false - 非法
	 * @throws IllegalAccessException
	 * @throws BeanUtilException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private static void checkTextLegal(Field f, Object target, Require r)
			throws IllegalAccessException, BeanUtilException,
			NoSuchMethodException, InvocationTargetException {
		Object o = MethodUtils.invokeMethod(target,
				handleGetMethodName(f.getName()), null);
		if (o == null) {
			throw new BeanUtilException("参数[" + target.getClass() + ":"
					+ (StringUtils.isEmpty(r.name()) ? f.getName() : r.name())
					+ "]值为空");
		} else if (String.class.isAssignableFrom(o.getClass())) {
			checkStringNull(f, target, r);
		} else if (Collection.class.isAssignableFrom(o.getClass())) {
			checkCollectionNull(f, target, r);
		} else if (Map.class.isAssignableFrom(o.getClass())) {
			checkMapNull(f, target, r);
		} else if (r.genericsClass().isAssignableFrom(o.getClass())) {
			checkObjectLegal(o);
		} else {
			throw new BeanUtilException("不支持验证类型，参数[" + target.getClass() + ":"
					+ (StringUtils.isEmpty(r.name()) ? f.getName() : r.name())
					+ "]类型[" + o.getClass().getName() + "]");
		}
	}

	/**
	 * 验证集合内容是否为空
	 * 
	 * @return
	 * @throws IllegalAccessException
	 * @throws BeanUtilException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private static void checkCollectionNull(Field f, Object target, Require r)
			throws IllegalAccessException, BeanUtilException,
			NoSuchMethodException, InvocationTargetException {
		Collection<?> o = (Collection<?>) MethodUtils.invokeMethod(target,
				handleGetMethodName(f.getName()), null);
		if (o == null || o.size() == 0) {
			throw new BeanUtilException("参数[" + target.getClass() + ":"
					+ (StringUtils.isEmpty(r.name()) ? f.getName() : r.name())
					+ "]值为空");
		}
		Iterator<?> it = o.iterator();
		while (it.hasNext()) {
			checkObjectLegal(it.next());
		}
	}

	/**
	 * 验证Map内容是否为空
	 * 
	 * @param o
	 * @return
	 * @throws IllegalAccessException
	 * @throws BeanUtilException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private static void checkMapNull(Field f, Object target, Require r)
			throws IllegalAccessException, BeanUtilException,
			NoSuchMethodException, InvocationTargetException {
		Map<?, ?> o = (Map<?, ?>) MethodUtils.invokeMethod(target,
				handleGetMethodName(f.getName()), null);
		if (o == null || o.size() == 0) {
			throw new BeanUtilException("参数[" + target.getClass() + ":"
					+ (StringUtils.isEmpty(r.name()) ? f.getName() : r.name())
					+ "]值为空");
		}
		Set<?> s = o.entrySet();
		Iterator<?> it = s.iterator();
		while (it.hasNext()) {
			Map.Entry<?, ?> e = (Map.Entry<?, ?>) it.next();
			if (e.getValue().getClass().isAssignableFrom(String.class)) {
				checkStringNull(f, e, r);
			} else {
				checkObjectLegal(e.getValue());
			}
		}
	}

	/**
	 * @param e
	 * @throws BeanUtilException
	 */
	private static void checkStringNull(Field f, Entry<?, ?> e, Require r)
			throws BeanUtilException {
		@SuppressWarnings("unchecked")
		Entry<?, String> ie = (Entry<?, String>) e;
		String str = StringUtils.strip(ie.getValue());
		if (StringUtils.isEmpty(str)) {
			throw new BeanUtilException("参数["
					+ (StringUtils.isEmpty(r.name()) ? f.getName() : r.name())
					+ "]键[" + e.getKey() + "]对应的值为空");
		}
		ie.setValue(str);
	}

	/**
	 * 验证字符串是否为空
	 * 
	 * @param o
	 * @return
	 * @throws IllegalAccessException
	 * @throws BeanUtilException
	 * @throws InvocationTargetException
	 * @throws NoSuchMethodException
	 */
	private static String checkStringNull(Field f, Object target, Require r)
			throws BeanUtilException, NoSuchMethodException,
			InvocationTargetException, IllegalAccessException {
		Object o = MethodUtils.invokeMethod(target,
				handleGetMethodName(f.getName()), null);
		if (o == null) {
			throw new BeanUtilException("[checkStringNull]参数["
					+ target.getClass() + ":"
					+ (StringUtils.isEmpty(r.name()) ? f.getName() : r.name())
					+ "]值为空");
		}
		if (o instanceof String) {
			String str = StringUtils.strip((String) o);
			if (StringUtils.isEmpty(str)) {
				throw new BeanUtilException("参数["
						+ target.getClass()
						+ ":"
						+ (StringUtils.isEmpty(r.name()) ? f.getName()
								: r.name()) + "]值为空");
			} else {
				MethodUtils.invokeMethod(target,
						handleSetMethodName(f.getName()), str);
				return str;
			}
		} else {
			throw new BeanUtilException("[checkStringNull]参数["
					+ target.getClass() + ":"
					+ (StringUtils.isEmpty(r.name()) ? f.getName() : r.name())
					+ "]不是[" + String.class.getName() + "]类型");
		}
	}

	/**
	 * @param f
	 * @return
	 */
	public static String handleGetMethodName(String fieldName) {
		StringBuilder sb = new StringBuilder();
		sb.append("get");
		sb.append(fieldName.substring(0, 1).toUpperCase());
		sb.append(fieldName.substring(1, fieldName.length()));
		return sb.toString();
	}

	/**
	 * @param f
	 * @return
	 */
	public static String handleSetMethodName(String fieldName) {
		StringBuilder sb = new StringBuilder();
		sb.append("set");
		sb.append(fieldName.substring(0, 1).toUpperCase());
		sb.append(fieldName.substring(1, fieldName.length()));
		return sb.toString();
	}

	@Documented
	// 该注解将被包含在javadoc中
	/**
	 * @Retention: 定义注解的保留策略
	 * @Retention(RetentionPolicy.SOURCE)   //注解仅存在于源码中，在class字节码文件中不包含
	 * @Retention(RetentionPolicy.CLASS)     // 默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得，
	 * @Retention(RetentionPolicy.RUNTIME)  // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
	 */
	@Retention(RetentionPolicy.RUNTIME)
	/**
	 * @Target：定义注解的作用目标
	 * @author liuyi
	 * @Target(ElementType.TYPE)   //接口、类、枚举、注解
	 * @Target(ElementType.FIELD) //字段、枚举的常量
	 * @Target(ElementType.METHOD) //方法
	 * @Target(ElementType.PARAMETER) //方法参数
	 * @Target(ElementType.CONSTRUCTOR)  //构造函数
	 * @Target(ElementType.LOCAL_VARIABLE)//局部变量
	 * @Target(ElementType.ANNOTATION_TYPE)//注解
	 * @Target(ElementType.PACKAGE) //包   
	 */
	@Target(ElementType.FIELD)
	// 子类可以继承父类所定义的该注解
	@Inherited
	/**
	 * 该注解使用在BEAN的YEILD属性上，用以标识为依赖属性。
	 * @author yi_liu
	 */
	public @interface Require {

		public enum AttributeType {
			// 数字，先验证是否为数字，然后调用checkContent正则法则验证
			NUMBERIC,
			// 文本，字符串，调用checkContent正则法则验证
			TEXT,
			// 时间，要验证的时间类型必须为String
			TIME,
			// 无验证规则，只进行非空验证
			NONE
		};

		// 错误日志内显示的名称
		public String name() default "";

		// 去除前后空格开关
		public boolean stripFlag() default true;

		// 内容校验工具，正则法则
		public String checkContent() default "";

		// 被验证属性的类型
		public AttributeType contentType() default AttributeType.TEXT;

		// 时间格式
		public String timeForm() default "yyyyMMddHHmmss";

		// 参数类型
		public Class<?> genericsClass() default String.class;
	}

	@Documented
	// 该注解将被包含在javadoc中
	/**
	 * @Retention: 定义注解的保留策略
	 * @Retention(RetentionPolicy.SOURCE)   //注解仅存在于源码中，在class字节码文件中不包含
	 * @Retention(RetentionPolicy.CLASS)     // 默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得，
	 * @Retention(RetentionPolicy.RUNTIME)  // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
	 */
	@Retention(RetentionPolicy.RUNTIME)
	/**
	 * @Target：定义注解的作用目标
	 * @author liuyi
	 * @Target(ElementType.TYPE)   //接口、类、枚举、注解
	 * @Target(ElementType.FIELD) //字段、枚举的常量
	 * @Target(ElementType.METHOD) //方法
	 * @Target(ElementType.PARAMETER) //方法参数
	 * @Target(ElementType.CONSTRUCTOR)  //构造函数
	 * @Target(ElementType.LOCAL_VARIABLE)//局部变量
	 * @Target(ElementType.ANNOTATION_TYPE)//注解
	 * @Target(ElementType.PACKAGE) //包   
	 */
	@Target(ElementType.TYPE)
	// 子类可以继承父类所定义的该注解
	@Inherited
	/**
	 * 该注解使用在class上以标识为需要验证的bean
	 * @author yi_liu
	 */
	@Component
	public @interface CheckBean {
	}

	@Documented
	// 该注解将被包含在javadoc中
	/**
	 * @Retention: 定义注解的保留策略
	 * @Retention(RetentionPolicy.SOURCE)   //注解仅存在于源码中，在class字节码文件中不包含
	 * @Retention(RetentionPolicy.CLASS)     // 默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得，
	 * @Retention(RetentionPolicy.RUNTIME)  // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
	 */
	@Retention(RetentionPolicy.RUNTIME)
	/**
	 * @Target：定义注解的作用目标
	 * @author liuyi
	 * @Target(ElementType.TYPE)   //接口、类、枚举、注解
	 * @Target(ElementType.FIELD) //字段、枚举的常量
	 * @Target(ElementType.METHOD) //方法
	 * @Target(ElementType.PARAMETER) //方法参数
	 * @Target(ElementType.CONSTRUCTOR)  //构造函数
	 * @Target(ElementType.LOCAL_VARIABLE)//局部变量
	 * @Target(ElementType.ANNOTATION_TYPE)//注解
	 * @Target(ElementType.PACKAGE) //包   
	 */
	@Target(ElementType.METHOD)
	// 子类可以继承父类所定义的该注解
	@Inherited
	/**
	 * 验证方法，在验证时将回调该注解指定的方法
	 * @author yi_liu
	 */
	public @interface CheckMethod {
	}

	@Documented
	// 该注解将被包含在javadoc中
	/**
	 * @Retention: 定义注解的保留策略
	 * @Retention(RetentionPolicy.SOURCE)   //注解仅存在于源码中，在class字节码文件中不包含
	 * @Retention(RetentionPolicy.CLASS)     // 默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得，
	 * @Retention(RetentionPolicy.RUNTIME)  // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
	 */
	@Retention(RetentionPolicy.RUNTIME)
	/**
	 * @Target：定义注解的作用目标
	 * @author liuyi
	 * @Target(ElementType.TYPE)   //接口、类、枚举、注解
	 * @Target(ElementType.FIELD) //字段、枚举的常量
	 * @Target(ElementType.METHOD) //方法
	 * @Target(ElementType.PARAMETER) //方法参数
	 * @Target(ElementType.CONSTRUCTOR)  //构造函数
	 * @Target(ElementType.LOCAL_VARIABLE)//局部变量
	 * @Target(ElementType.ANNOTATION_TYPE)//注解
	 * @Target(ElementType.PACKAGE) //包   
	 */
	@Target(ElementType.METHOD)
	// 子类可以继承父类所定义的该注解
	@Inherited
	/**
	 * 验证前会调用该注解指定的方法
	 * @author yi_liu
	 */
	public @interface CheckBefore {
	}

	@Documented
	// 该注解将被包含在javadoc中
	/**
	 * @Retention: 定义注解的保留策略
	 * @Retention(RetentionPolicy.SOURCE)   //注解仅存在于源码中，在class字节码文件中不包含
	 * @Retention(RetentionPolicy.CLASS)     // 默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得，
	 * @Retention(RetentionPolicy.RUNTIME)  // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
	 */
	@Retention(RetentionPolicy.RUNTIME)
	/**
	 * @Target：定义注解的作用目标
	 * @author liuyi
	 * @Target(ElementType.TYPE)   //接口、类、枚举、注解
	 * @Target(ElementType.FIELD) //字段、枚举的常量
	 * @Target(ElementType.METHOD) //方法
	 * @Target(ElementType.PARAMETER) //方法参数
	 * @Target(ElementType.CONSTRUCTOR)  //构造函数
	 * @Target(ElementType.LOCAL_VARIABLE)//局部变量
	 * @Target(ElementType.ANNOTATION_TYPE)//注解
	 * @Target(ElementType.PACKAGE) //包   
	 */
	@Target(ElementType.METHOD)
	// 子类可以继承父类所定义的该注解
	@Inherited
	/**
	 * 验证后会调用该注解指定的方法
	 * @author yi_liu
	 */
	public @interface CheckAfter {
	}

	public static void main(String[] args) throws Exception {
		// try {
		// byte[] b = Base64Utility
		// .decode("ew0KICAidGV4dCIgOiAi5rWL6K+V5YaF5a65Ig0KfQ==");
		// String s = new String(b, "UTF-8");
		// System.out.println(s);
		// } catch (Base64Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// try {
		// System.out.println(URLEncoder.encode("沈阳", "UTF-8"));
		// } catch (UnsupportedEncodingException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	public void TestCompress() throws Exception {
		int c = 1;
		for (int i = 0; i < 200; i++) {
			test(c = 2 * c);
		}
//		test(23);
	}

	/**
	 * @param count
	 * @throws Exception
	 */
	public static void test(int count) throws Exception {
		byte[] in = new byte[count];
		for (int i = 0; i < count; i++) {
			in[i] = 'b';
		}
		Date start = new Date();
		byte[] out = GZipUtils.compress(in);
		Date end = new Date();
		BigDecimal ol = new BigDecimal(out.length);
		BigDecimal il = new BigDecimal(in.length);
		System.out.println("压缩前:" + il);
		System.out.println("压缩后:" + ol);
		System.out.println("压缩比:" + il.divide(ol, 3, BigDecimal.ROUND_HALF_UP));
		System.out.println("耗费时间:" + (end.getTime() - start.getTime()));
		System.out.println("----------------------------------");
	}
}
