package com.bycompany.persistence.repository;

import com.bycompany.persistence.entity.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerCrudRepository extends CrudRepository<Customer, Long> {
    List<Customer> findByUsername(String username);

    Optional<Customer> searchByUsername(String username);

    List<Customer> findByNameContaining(String string);

    List<Customer> findByNameStartingWith(String string);

    List<Customer> readByNameEndingWith(String mane);

    List<Customer> findByNameContainingAndIdGreaterThanOrderByIdDesc(String name, Long id);
}
