package com.resjz.common.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MapUtil {

    private static final String INTEGER_NAME = "java.lang.Integer";
    private static final String BOOLEAN_NAME = "java.lang.Boolean";
    private static final String LONG_NAME = "java.lang.Long";
    private static final String STRING_NAME = "java.lang.String";
    private static final String BYTE_NAME = "java.lang.Byte";
    private static final String SHORT_NAME = "java.lang.Short";
    private static final String FLOAT_NAME = "java.lang.Float";
    private static final String DOUBLE_NAME = "java.lang.Double";

    /**
     * Map --> JavaBean
     * @param javaBean 实例对象
     * @param map
     * @param isCamel true:map的key是驼峰 false：map的key是下划线
     * @return
     */
    @SuppressWarnings({ "rawtypes","unchecked", "hiding" })
    public static <T> T map2JavaBean(T javaBean, Map map, boolean isCamel) {
        try {
            // 获取javaBean属性
            BeanInfo beanInfo = Introspector.getBeanInfo(javaBean.getClass());
            // 创建 JavaBean 对象
            Object obj = javaBean.getClass().newInstance();

            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            if (Objects.nonNull(propertyDescriptors) && propertyDescriptors.length > 0) {
                // javaBean属性名
                String propertyName = null;
                // javaBean属性值
                Object propertyValue = null;
                for (PropertyDescriptor pd : propertyDescriptors) {
//                    String STR = GuavaUtils.camelToUnderscore(pd.getName());
                    propertyName = isCamel ? pd.getName() :pd.getName();
                    if (map.containsKey(propertyName)) {
                        if ( map.get(propertyName).equals("")|| map.get(propertyName)==null){
                            continue;
                        }
                        propertyValue = get(pd.getPropertyType(), map.get(propertyName));
                        pd.getWriteMethod().invoke(obj, new Object[] { propertyValue });
                    }
                }
                return (T) obj;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 注：不支持char类型
     * @param clz
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> T get(Class<T> clz, Object obj) {
        String clzName = clz.getName();
        switch (clzName) {
            case BOOLEAN_NAME :
                return (T) Boolean.valueOf(obj.toString());
            case INTEGER_NAME :
                return (T) Integer.valueOf(obj.toString());
            case LONG_NAME :
                return (T) Long.valueOf(obj.toString());
            case STRING_NAME :
                return (T) String.valueOf(obj.toString());
            case BYTE_NAME :
                return (T) Byte.valueOf(obj.toString());
            case SHORT_NAME :
                return (T) Short.valueOf(obj.toString());
            case FLOAT_NAME :
                return (T) Float.valueOf(obj.toString());
            case DOUBLE_NAME :
                return (T) Double.valueOf(obj.toString());
            default:
                return null;
        }

    }

    /**
     * Map集合对象转化成 JavaBean集合对象
     *
     * @param javaBean JavaBean实例对象
     * @param mapList Map数据集对象
     * @param isCamel true:map的key是驼峰 false：map的key是下划线
     * @return
     */
    @SuppressWarnings({ "rawtypes" })
    public static <T> List<T> mapList2JavaBeanList(T javaBean, List<Map> mapList, boolean isCamel) {
        if(mapList == null || mapList.isEmpty()){
            return null;
        }
        List<T> objectList = new ArrayList<T>();

        T object = null;
        for(Map map : mapList){
            if(Objects.nonNull(map)){
                object = map2JavaBean(javaBean, map, isCamel);
                objectList.add(object);
            }
        }

        return objectList;

    }
}
