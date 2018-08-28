package com.dvilela.match.customers.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dvilela.match.customers.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
	
	@Query("SELECT c FROM Customer c where distance < :distance ") 
	List<Customer> filterCustomersByDistance(@Param("distance") double distance) ;
}
