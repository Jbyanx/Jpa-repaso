package com.bycompany;

import com.bycompany.persistence.entity.Customer;
import com.bycompany.persistence.repository.CustomerCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}

	@Autowired
	private CustomerCrudRepository customerCrudRepository;
	@Bean
	public CommandLineRunner testCustomerRepository (){
		return args -> {
			Customer juan = new Customer();
			juan.setName("Juan perez");
			juan.setPassword("juan123");

			Customer pedro = new Customer();
			pedro.setName("Pedro villa");
			pedro.setPassword("pello321");

			Customer maria = new Customer();
			maria.setName("maria toloza");
			maria.setPassword("Mariuwu");

			List<Customer> customerList = List.of(juan,pedro,maria);

			System.out.println("\nGUARDANDO LOS 3 CLIENTES");
			customerCrudRepository.saveAll(customerList);

			System.out.println("\n=====IMPRIMIENDO LOS CUSTOMER DE LA BASE DE DATOS=====");
			customerCrudRepository.findAll()
					.forEach(System.out::println);

			System.out.println("\nBuscando y editando al cliente id 2");
			customerCrudRepository.findById(Long.valueOf(2))
				.ifPresent(customer -> {
					customer.setName("Pedro villa villazon");
					customer.setPassword("pellovilla123");

					customerCrudRepository.save(customer);
				});

			System.out.println("\nEliminando al cliente 1");
			customerCrudRepository.deleteById(Long.valueOf(1));

			System.out.println("\n=====IMPRIMIENDO LOS CUSTOMER DE LA BASE DE DATOS=====");
			customerCrudRepository.findAll()
					.forEach(System.out::println);
		};
	}
}
