package com.bycompany.persistence.repository;

import com.bycompany.persistence.entity.Customer;
import org.springframework.data.jpa.repository.Query;
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

    @Query("SELECT c FROM Customer c WHERE c.name LIKE %?1% AND c.id >= ?2 ORDER BY c.id DESC")
    List<Customer> findByNameAndIdGreaterThan(String name, Long id);

    @Query(value = "SELECT c.* FROM clientes c WHERE c.nombre LIKE %?1% AND c.id >= ?2 ORDER BY c.usuario DESC", nativeQuery = true)
    List<Customer> findByNameAndIdGreaterThanUsingNativeSQL(String name, Long id);
}
