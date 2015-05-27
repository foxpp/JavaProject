package com.anotation.jdbc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface JDBCAnnotation {
	public String url() default "";
	public String dbName() default "";
	public String user() default "";
	public String password() default "";
}
