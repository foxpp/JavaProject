package com.anotation.test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;



public class AnotationTest {
	
	@MethodAnnotation(name="public method call lily",description="a method for test")
	public String callLily(){
		return "called lily";
	}
	
	@MethodAnnotation
	public void noAnnotationMethodTest(){
		for (int i = 0; i < 10; i++) {
			System.out.println("no anotation : " + i);
		}
	}
	
	public static void printSingleLine(){
		System.out.println("-----------------------------------------");
	}
	
	public static void printClassMethodsName(){
		
		for (Method method : AnotationTest.class.getMethods()) {
			System.out.println("method name: "  + method.getName());
			for (Class<?> para: method.getParameterTypes()) {
				System.out.println("para: " + para.getName());
			}
			if (method.getParameterTypes().length == 0) {
				System.out.println("no para! ");
			}
			printSingleLine();
		}
		
	}
	
	public static void findAnnotationMethods(){
		Method[]  methods = AnotationTest.class.getDeclaredMethods();
		for (Method method : methods) {
			MethodAnnotation methodAnnotation = method.getAnnotation(MethodAnnotation.class);
			
			if (methodAnnotation != null) {
				System.out.println(methodAnnotation.name() + " | " + methodAnnotation.description());
			}else {
				try {
					if (method.getName().equals("noAnnotationMethodTest")) {
						method.invoke(AnotationTest.class.newInstance());
					}
					
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InstantiationException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	static class InnerStaticCls{
		@MethodAnnotation(name = "inner class method")
		public void innerMethod(){
			
		}
	}
	
	public static void findDeclaredClass(){
		Class<?> []classes = AnotationTest.class.getDeclaredClasses();
		for (Class<?> cls : classes) {
			System.out.println(cls.getName());
		}
	}
	
	public static void main(String[] args) {
		findAnnotationMethods();
		printSingleLine();
		printClassMethodsName();
		printSingleLine();
		findDeclaredClass();
	}
}
