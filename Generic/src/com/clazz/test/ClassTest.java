package com.clazz.test;

import sun.reflect.Reflection;



public class ClassTest {
	Class<?> clazz =  new Object().getClass();

	/**
	 * 通过类的路径来找到元类(Class),在类路径拼写错误的情况下，会抛出找不到类的异常
	 */
	
	static void forNameTest(){
		System.out.println("--------------------------forNameTest--------------------------");
		try {
			
			Class<?> testClz = Class.forName("java.lang.String");
			System.out.println(testClz.getName());
			System.out.println(testClz.getSimpleName());
			
			Class<?> myCls = Class.forName("com.clazz.test.ClassTest");
			System.out.println(myCls.getName());
			System.out.println(myCls.getSimpleName());
			
			//另一种forname,实际上上面的调用的也是这个，只是参数有默认的
			//最后一个参数必须和类路径的类一样,JDK 中调用的是反射机制下的当前类调用者
			System.out.println("JDK源码中调用的classloader : " + Reflection.getCallerClass().getName());
			
			//每个对象都可以获取自己元类中的类加载器
			System.out.println("自己获取classloader: " +  ClassTest.class.getClassLoader());
			
			//通过系统获取
		    Class<?> testClz2 = Class.forName("com.clazz.test.ClassTest", true, ClassLoader.getSystemClassLoader());
		    
		    /*TODO: 待研究JVM
		      	有三种不同类型的Classloader
		      	bootstrap classloader
            			|
   				extension classloader
            			|
   				system classloader
   				
   				系统在用classloader加载类时，
   				会检查具体的权限，为防止用户自己写classloader来破坏java的安全机制等
   				SecurityManager.checkPermission(SecurityConstants.GET_CLASSLOADER_PERMISSION);
   				其中会涉及到 AccessControlContext （JVM控制）来检查
   				如果不通过，会调用 forName0 直接抛出classnotfound的异常
   				*/
		    System.out.println("forName2 : " + testClz2.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	static void newInstanceTest(){
		System.out.println("--------------------------newInstanceTest--------------------------");
		Class<?> test = String.class;
		try {
			/**
			 *  在newInstance的过程中，内部会去检查访问权限 
			 *  checkMemberAccess(Member.PUBLIC, Reflection.getCallerClass(), false);
			 *  然后再次由其他系统反射机制去检查
			 *      if (!Reflection.quickCheckMemberAccess(this, modifiers)) {
             *			Class<?> caller = Reflection.getCallerClass();
             * 			if (newInstanceCallerCache != caller) {
             *   				 Reflection.ensureMemberAccess(caller, this, null, modifiers);
             *  					  newInstanceCallerCache = caller;
             *			}
             *		}
			 *  然后会去查找构造函数 
			 *  final Constructor<T> c = getConstructor0(empty, Member.DECLARED);
			 *  最后由实际的构造函数对象（Constructor<T>)来完成对象的建立
			 *  tmpConstructor.newInstance((Object[])null);
			 *  
			 *  Class中对构造器和Caller的类会做缓存
			 *    private volatile transient Constructor<T> cachedConstructor;
    
                  private volatile transient Class<?>       newInstanceCallerCache;
			 */
			String string = (String) test.newInstance();
			string = "test string";
			System.out.println(string);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	static void superClassTest(){
		System.out.println("--------------------------superClassTest--------------------------");
		System.out.println(ClassTest.class.getSuperclass().getName());
		/**
		 * Object作为整个类的顶端，再往上是空指针，这里和MFC的类目树机制一样，猜测是也是由 “子到父”的单向链表组成
		 */
		System.out.println(Object.class.getSuperclass());
	}
	
	public static void main(String[] args){
		forNameTest();
		newInstanceTest();
		superClassTest();
	}
	

}
