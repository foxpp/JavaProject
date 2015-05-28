package com.generic.simple;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

import com.generic.testbeans.FooMan;

public class SelfTest {
	
	public static final String FINAL_STRING = "final test string";
	public String abc;
	int pack;
	
	public SelfTest(){
		
	}
	
	void foo(int a ,int b){
		
	}
	
	static void printClassMethods(Class<?> clazz){
		System.out.println("Methods: ");
		Method[] methods = clazz.getDeclaredMethods();
		for (Method method : methods) {
			StringBuffer stringBuffer = new StringBuffer();	
			String access  = Modifier.toString(method.getModifiers());
			stringBuffer.append( (access.isEmpty() ? "package " : access )+ " | ");
			stringBuffer.append(method.getReturnType().getName()+ " - "  +method.getName());
			stringBuffer.append("(");
			Class<?> []paras  =  method.getParameterTypes();
			for (int i = 0; i < paras.length; ++i) {
				Class<?> para = paras[i];			
				stringBuffer.append(para.getSimpleName());
				if (i != paras.length-1) {
					stringBuffer.append(", ");
				}
			}
			stringBuffer.append(")");
			System.out.println(stringBuffer);	
		}
	}
	
	static void printClassMember(Class<?> clazz){
		System.out.println("Members: ");
		Field [] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			StringBuffer buffer = new StringBuffer();
			String access  = Modifier.toString(field.getModifiers());
			buffer.append((access.isEmpty() ? "package " : access ) + " " + field.getName());
			System.out.println(buffer);
		}
	}
	
	static void printClassInfo(Class<?> clazz){
		System.out.println(clazz.getName());
		printClassMethods(clazz);
		printClassMember(clazz);
		System.out.println("----------------------------------");
	}
	
	public static void main(String[] args) {
		printClassInfo(SelfTest.class);
		printClassInfo(ArrayList.class);
		printClassInfo(Array.class);
		printClassInfo(FooMan.class);
	}
}
