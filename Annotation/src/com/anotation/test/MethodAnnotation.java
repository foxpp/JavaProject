package com.anotation.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface MethodAnnotation {
	public static final String ANNOTATION_NAME = "MethodNannotaion";
	public String name() default "method";
	public String description() default "descripton";
}
