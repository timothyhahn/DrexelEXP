package com.drexelexp.customer;

import com.drexelexp.customer.Customer;

public interface CustomerDAO 
{
	public void insert(Customer customer);
	public Customer findByCustomerId(int custId);
}