package com.bycompany;

import com.bycompany.persistence.entity.Customer;
import com.bycompany.persistence.repository.CustomerCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
//			Customer juan = new Customer();
//			juan.setName("Juan");
//			juan.setPassword("juan123");
//
//			customerCrudRepository.save(juan);
//			System.out.println("Se guardo la entidad juan");

//			System.out.println("=====IMPRIMIENDO LOS CUSTOMER DE LA BASE DE DATOS=====");
//			customerCrudRepository.findAll()
//					.forEach(each -> System.out.println(each));

			System.out.println("\nBuscando e imprimiendo al cliente id 1");
			customerCrudRepository.findById(1L)
				.ifPresent(System.out::println);

			System.out.println("\nEliminando al cliente juan");
			customerCrudRepository.deleteById(1L);

			System.out.println("\nBuscando e imprimiendo al cliente id 1");
			customerCrudRepository.findById(1L)
				.ifPresent(System.out::println);
		};
	}
}
