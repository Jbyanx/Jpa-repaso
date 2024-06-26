package com.bycompany;

import com.bycompany.persistence.entity.Customer;
import com.bycompany.persistence.repository.CustomerCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}

	@Autowired
	private CustomerCrudRepository customerCrudRepository;
	@Bean
	public CommandLineRunner testQueryMethodsCommand (){
		return args -> {
			Customer juan = new Customer();
			juan.setName("Juan perez");
			juan.setPassword("juan123");
			juan.setUsername("juan123");

			Customer jose = new Customer();
			jose.setName("Jose ortiz");
			jose.setPassword("joselo");
			jose.setUsername("juooo");

			Customer pedro = new Customer();
			pedro.setName("Pedro perez");
			pedro.setPassword("pello321");
			pedro.setUsername("pello321");

			Customer maria = new Customer();
			maria.setName("maria toloza");
			maria.setPassword("Mariuwu");
			maria.setUsername("Mariuwu");

			Customer maria2 = new Customer();
			maria2.setName("maria villamizar");
			maria2.setPassword("mariaaaa");
			maria2.setUsername("Mariawa");

			List<Customer> customerList = List.of(juan, jose, pedro,maria,maria2);

			System.out.println("\nGUARDANDO LOS 4 CLIENTES");
			customerCrudRepository.saveAll(customerList);

//			System.out.println("\nPROBANDO NOMBRES QUE CONTENGAN 'E'");
//			customerCrudRepository.findByNameContaining("e")
//					.forEach(System.out::println);

//            System.out.println("\nPROBANDO NOMBRES QUE EMPIECEN CON 'j'");
//			customerCrudRepository.findByNameStartingWith("j")
//					.forEach(System.out::println);



//			System.out.println("\nPROBANDO NOMBRES QUE TERMINAN CON 'z' ");
//			customerCrudRepository.readByNameEndingWith("z")
//					.forEach(System.out::println);

//            System.out.println("\nPROBANDO FIND BY USERNAME ");

//			customerCrudRepository.findByUsername("Mariuwu")
//					.forEach(System.out::println);
//
//			System.out.println("\nPROBANDO SEARCH BY USERNAME ");
//			System.out.println(customerCrudRepository.searchByUsername("Mariuwu"));
//


			System.out.println("\nPROBANDO clientes que contengan 'ez' y el Id sea mayor a 2");
			customerCrudRepository.findByNameContainingAndIdGreaterThanOrderByIdDesc("ez", 2L)
					.forEach(System.out::println);
		};
	}
}
