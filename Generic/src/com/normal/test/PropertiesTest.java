package com.normal.test;

import java.io.IOException;
import java.util.Properties;

/**
 * 本质上Properties只是带了读取文件功能的增强版MAP
 * 内嵌了LineReader来实现扫描文件，以及拆分等号两边生成key，value
 * @author thinkpad
 *
 */
public class PropertiesTest {
	public static void main(String[] args) throws IOException {
		Properties properties = new Properties();
		properties.load(Object.class.getResourceAsStream("/Words"));
		properties.list(System.out);
		for (String string : properties.stringPropertyNames()) {
			System.out.println(string);
		}
		
	}
}
