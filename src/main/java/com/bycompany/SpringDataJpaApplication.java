package com.bycompany;

import com.bycompany.persistence.entity.Adress;
import com.bycompany.persistence.entity.Customer;
import com.bycompany.persistence.repository.AdressCrudRepository;
import com.bycompany.persistence.repository.CustomerCrudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@SpringBootApplication
public class SpringDataJpaApplication {
	@Autowired
	private CustomerCrudRepository customerCrudRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}


	@Bean
	@Order(1)
	public CommandLineRunner testOneToOneRelationshipCommand (){
		return args -> {
			//creamos un cliente
			Customer jabes = new Customer();
			jabes.setName("Jabes Borre");
			jabes.setPassword("Jabesinho");
			jabes.setUsername("jabys");
			//primera direccion del cliente
			Adress jabesAdress1 = new Adress();
			jabesAdress1.setCountry("Colombia");
			jabesAdress1.setAdress("Manzana O casa 15, Timayui 2, Santa Marta, Magdalena");
			//segunda direccion del cliente
			Adress jabesAdress2 = new Adress();
			jabesAdress2.setCountry("Colombia");
			jabesAdress2.setAdress("Calle 8f 31-03, 17 Dic, Santa Marta, Magdalena");

			//seteamos las direcciones al cliente
			jabes.setAdresses(List.of(jabesAdress1, jabesAdress2));

			//guardamos el cliente
			customerCrudRepository.save(jabes);
		};
	}

	@Bean
	@Order(2)
	public CommandLineRunner testListAllCustomers(){
		return args -> {
			System.out.println(customerCrudRepository.findAll());
		};
	}
}
