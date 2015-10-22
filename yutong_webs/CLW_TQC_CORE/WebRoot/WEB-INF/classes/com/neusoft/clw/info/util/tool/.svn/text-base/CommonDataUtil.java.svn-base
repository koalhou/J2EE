package com.neusoft.clw.info.util.tool;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class CommonDataUtil {

    /**
     * 通过反射获取某个javabean对象中某个字段的值
     * @param obj 对象实例
     * @param fieldName 字段名
     * @return 字段值
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    @SuppressWarnings("unchecked")
    public static Object getFieldValueOfObj(Object obj, String fieldName) throws SecurityException,
            NoSuchMethodException, IllegalArgumentException, IllegalAccessException,
            InvocationTargetException {
        Class classType = obj.getClass();
        String stringLetter = fieldName.substring(0, 1).toUpperCase();
        String getName = "get" + stringLetter + fieldName.substring(1);
        Method getMethod = classType.getMethod(getName, new Class[] {});
        Object value = getMethod.invoke(obj, new Object[] {});
        return value;
    }

    /**
     * 深度拷贝对象
     * @param obj 被拷贝的对象
     * @return 对象的拷贝
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws LcsJobBusinessException
     */
    public static Object depthClone(Object obj) throws Exception {
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream out = new ObjectOutputStream(byteOut);
            out.writeObject(obj);
            ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
            ObjectInputStream in = new ObjectInputStream(byteIn);
            return in.readObject();
        } catch (Exception e) {
            throw new Exception("拷贝对象时出现异常:" + e);
        }
    }
}
