package com.bycompany.persistence.repository;

import com.bycompany.persistence.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface CustomerCrudRepository extends CrudRepository<Customer, Long> {

}
