package com.itheima.app_5utils;

import java.util.Date;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;

public class MyBeanUtilsPlus {
	public static <T> T populate(Class<T> clazz, Map<String, String[]> map) throws Exception {
		T t = clazz.newInstance();
		// 创建日期转换器类
		DateConverter dc = new DateConverter();
		// 定义转换格式
		dc.setPatterns(new String[] { "yyyy-MM-dd", "yyyy/MM/dd", "yyyy年MM月dd日", "yyyy年MM月dd" });
		// 注册转换器
		ConvertUtils.register(dc, Date.class);
		// 封装对象
		BeanUtils.populate(t, map);
		return t;
	}
}
