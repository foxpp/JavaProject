package com.dp.example;

public class ThreadErrorDemo implements Runnable {
	public static int i = 0;

	synchronized void print() {
		i++;
		System.out.println("i :" + i);

	}

	@Override
	public void run() {
		print();
	}

	/**
	 * 这里生成了十个ThreadErrorDemo对象，只能保证各个对象各自的数据是互斥的
	 * 但是保护不了共享变量i
	 * 因为synchronized加在方法上默认只是针对对象的级别而言
	 * 相当于在代码块中synchronized(this)
	 * 只要多运行几次，就会发现结果是错误的
	 */
	static void errorSynchronized(){
		for (int i = 0; i < 10; i++) {
			new Thread(new ThreadErrorDemo()).start();
		}
	}
	
	/**
	 * 只有一个对象，十个线程共用，正确的例子
	 * 猜测使用了操作系统中的信号量，并非互斥量来实现
	 */
	static void goodSynchronized(){
		ThreadErrorDemo errorDemo = new ThreadErrorDemo();
		for (int i = 0; i < 10; i++) {
			new Thread(errorDemo).start();
		}
	}
	public static void main(String[] args) {
		goodSynchronized();
	}
}
