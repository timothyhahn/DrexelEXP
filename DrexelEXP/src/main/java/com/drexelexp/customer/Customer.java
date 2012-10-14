package com.drexelexp.customer;


public class Customer 
{
	int custId;
	String name;
	int age;
	//getter and setter methods
	public Customer(int id, String name, int age) {
		this.custId = id;
		this.name = name;
		this.age = age;
	}
	public int getCustId() {
		return custId;
	}
	public String getName() {
		return name;
	}
	public int getAge() {
		return age;
	}
}