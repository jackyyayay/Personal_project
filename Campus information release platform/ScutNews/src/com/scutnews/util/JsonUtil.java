package com.scutnews.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

public class JsonUtil {

	public static String toJson(String result){
		JSONObject json = new JSONObject();
		json.put("Result", result);
		return json.toJSONString();
	}
	
	public static String toJson(String result,String message){
		JSONObject json = new JSONObject();
		json.put("Result", result);
		json.put("Message", message);
		return json.toJSONString();
	}
	
	public static String toJson(Object object){
		JSONObject json = new JSONObject();
		json = setAllComponentsName(object);
		return json.toJSONString();
	}
	
	public static String toJson(List list){
		JSONObject json = new JSONObject();
		json = setComponentList(list);
		return json.toJSONString();
	}
	public static String toJson(List list,int totalPage){
		JSONObject json = new JSONObject();
		json = setComponentList(list);
		json.put("totalPage", totalPage);
		return json.toJSONString();
	}
	
	public static JSONObject setComponentList(List list)// 将一个包含对象的列表转换成json
	{
		JSONObject json = new JSONObject();
		List listObject = new ArrayList();
		// for(Object model :list)
		for (Iterator ite = list.iterator(); ite.hasNext();) {
			Object model = ite.next();
			Field[] fields = model.getClass().getDeclaredFields();
			Map map = extractAllFields(model, fields);
			listObject.add(map);
		}
		json.put("Record",listObject);
		return json;
	}

	public static JSONObject setAllComponentsName(Object model) // 将这个对象转换为json类型
	{
		Field[] fields = model.getClass().getDeclaredFields();
		JSONObject json = new JSONObject();
		// List list = new ArrayList();
		Map map = extractAllFields(model, fields);
		// list.add(map);
		json.put("Record", map);

		return json;
	}

	private static Map extractAllFields(Object model, Field[] fields) {
		Map map = new HashMap();
		for (int i = 0; i < fields.length; i++) {
			Field field = fields[i];
			try {
				getFiled(model, map, field, "");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	public static JSONObject setComponentName(Object model, String[] varNames) // 将对象的某几个属性转换成json
	{
		JSONObject json = new JSONObject();
		Map map = new HashMap();
		List list = new ArrayList();

		for (String varName : varNames) {
			try {
				getFiled(model, map, varName, "");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		list.add(map);
		json.put("Records", list);
		return json;
	}

	private static void getFiled(Object model, Map map, String varName,
			String prefix)
			// 经对性的属性根据属性名称进行转换为json类型
			throws NoSuchMethodException, SecurityException,
			NoSuchFieldException, IllegalAccessException,
			InvocationTargetException {
		Field field = model.getClass().getDeclaredField(varName);
		getFiled(model, map, field, prefix);
	}

	private static void getFiled(Object model, Map map, Field field,
			String prefix)
			// 获得一个对象的一个属性
			throws NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		String varName = field.getName();
		String varNamem = varName.substring(0, 1).toUpperCase()
				+ varName.substring(1);
		String type = field.getGenericType().toString();

		if (type.equals("class java.lang.String")) { // 如果对象类型为string
			Method m = model.getClass().getMethod("get" + varNamem);
			String value = (String) m.invoke(model); // 调用方法获得ֵ
			if (value != null) {
				if ("".equals(prefix)) {
					map.put(varName, value);
				} else {
					map.put(prefix + "_" + varName, value);
				}
			}
		} else if (type.equals("class java.lang.Integer")) {
			Method m = model.getClass().getMethod("get" + varNamem);
			Integer value = (Integer) m.invoke(model);
			if (value != null) {
				if ("".equals(prefix)) {
					map.put(varName, value);
				} else {
					map.put(prefix + "_" + varName, value);
				}
			}
		} else if (type.equals("class java.lang.Short")) {
			Method m = model.getClass().getMethod("get" + varNamem);
			Short value = (Short) m.invoke(model);
			if (value != null) {
				if ("".equals(prefix)) {
					map.put(varName, value);
				} else {
					map.put(prefix + "_" + varName, value);
				}
			}
		} else if (type.equals("class java.lang.Double")) {
			Method m = model.getClass().getMethod("get" + varNamem);
			Double value = (Double) m.invoke(model);
			if (value != null) {
				if ("".equals(prefix)) {
					map.put(varName, value);
				} else {
					map.put(prefix + "_" + varName, value);
				}
			}
		} else if (type.equals("class java.lang.Boolean")) {
			Method m = model.getClass().getMethod("get" + varNamem);
			Boolean value = (Boolean) m.invoke(model);
			if (value != null) {
				if ("".equals(prefix)) {
					map.put(varName, value);
				} else {
					map.put(prefix + "_" + varName, value);
				}
			}
		} else if (type.equals("class java.util.Date")) {
			Method m = model.getClass().getMethod("get" + varNamem);
			Date value = (Date) m.invoke(model);
			if (value != null) {
				String valueStr = value.toLocaleString();
				if ("".equals(prefix)) {
					map.put(varName, valueStr);
				} else {
					map.put(prefix + "_" + varName, valueStr);
				}
			}
		} else if (type.equals("class java.sql.Time")) {
			Method m = model.getClass().getMethod("get" + varNamem);
			Time value = (Time) m.invoke(model);
			if (value != null) {
				String valueStr = value.toString();
				if ("".equals(prefix)) {
					map.put(varName, valueStr);
				} else {
					map.put(prefix + "_" + varName, valueStr);
				}
			}
		} else if (type.equals("class java.sql.Timestamp")) {
			Method m = model.getClass().getMethod("get" + varNamem);
			Timestamp value = (Timestamp) m.invoke(model);
			if (value != null) {
				String valueStr = value.toString();
				if ("".equals(prefix)) {
					map.put(varName, valueStr.split(" ")[0]);
				} else {
					map.put(prefix + "_" + varName, valueStr.split(" ")[0]);
				}
			}
		} else if (type.endsWith("interface java.util.Set"))// set属性 do nothing
		{

		} else
		// 若为外键
		{
			{
				Method m = model.getClass().getMethod("get" + varNamem);
				Object object = m.invoke(model);
				if (object != null) {
					Field[] fs = object.getClass().getDeclaredFields();// 首先获得ID
					for (Field f : fs) {
						if ("".equals(prefix)) {
							getFiled(object, map, f, varName);
						} else {
							getFiled(object, map, f, prefix + "_" + varName);
						}
					}
				}
			}
		}
	}
}
