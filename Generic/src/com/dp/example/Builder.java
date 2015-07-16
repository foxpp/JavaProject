package com.dp.example;

enum SEX{
	MAN, WOMAN
}

public class Builder {
	int age;
	String name;
	SEX sex;
	private Builder(InnerBuilder innerBuilder){
		this.age = innerBuilder.age;
		this.name = innerBuilder.name;
		this.sex = innerBuilder.sex;
	}
	
	@Override
	public String toString() {
		return "age: " + age + " name: "+ name + " sex: " +  sex;
	}
	public static class InnerBuilder{
		int age;
		String name;
		SEX sex;
		
		public InnerBuilder age(int age){
			this.age = age;
			return this;
		}
		
		public InnerBuilder name(String name){
			this.name = name;
			return this;
		}
		
		public InnerBuilder sex(SEX sex){
			this.sex = sex;
			return this;
		}
		
		public Builder build(){
			return new Builder(this);
		}
	}
	
	public static void main(String[] args) {
		Builder test = new Builder.InnerBuilder()
							.age(11)
							.sex(SEX.MAN)
							.name("John")
							.build();
		System.out.println(test);
	}
}
