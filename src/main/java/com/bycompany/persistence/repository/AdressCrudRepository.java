package com.bycompany.persistence.repository;

import com.bycompany.persistence.entity.Adress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AdressCrudRepository extends JpaRepository<Adress, Long> {

    List<Adress> findAdressByCustomerNameEndingWithIgnoreCase(String string);

}
