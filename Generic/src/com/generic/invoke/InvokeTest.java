package com.generic.invoke;

import java.lang.reflect.InvocationTargetException;



public class InvokeTest {

	public String saySomething(){
		return "fuck";
	}
		
	public Integer add(int x, int y){
		return x + y;
	}
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Class<?> innerCls = Class.forName("com.generic.invoke.InvokeTest");
		Object obj = innerCls.newInstance();
		java.lang.reflect.Method method = innerCls.getMethod("saySomething");
		System.out.println(method.invoke(obj));
		method = innerCls.getMethod("add", int.class, int.class);
		System.out.println(method.invoke(obj, 1,3 ));
	}
}
