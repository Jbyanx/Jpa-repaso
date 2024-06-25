package com.bycompany.persistence.repository;

import com.bycompany.persistence.entity.Adress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdressCrudRepository extends JpaRepository<Adress, Long> {

}
