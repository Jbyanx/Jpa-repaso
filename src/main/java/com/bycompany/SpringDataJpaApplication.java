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
import org.springframework.transaction.annotation.Transactional;

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

			//CAMINO 1
			//seteamos las direcciones al cliente
			//jabes.addAdress(jabesAdress1);
			//jabes.addAdress(jabesAdress2);

			//seteamos el customer a cada adress
			//jabesAdress1.setCustomer(jabes);
			//jabesAdress2.setCustomer(jabes);

			//guardar cliente
			//customerCrudRepository.save(jabes);

			//CAMINO 2
			//guardamos el cliente y 2 direcciones
			//jabes.guardarConDirecciones(customerCrudRepository, jabesAdress1, jabesAdress2);



		};
	}

//	@Bean
//	@Order(2)
//	public CommandLineRunner testListAllCustomers(AdressCrudRepository adressCrudRepository){
//		return args -> {
//			//System.out.println(adressCrudRepository.findAll());
//		};
//	}

	@Bean
	@Order(1)
	public CommandLineRunner testOneToManyRelationshipCommand(AdressCrudRepository adressCrudRepository){
		return args -> {
			//long id = 2l;
			adressCrudRepository.findAll()
				.forEach(each -> {
					System.out.println("\nPROBANDO RELACIONES BIDIRECCIONALES Direccion --> Customer");
					System.out.println("Direccion : "+ each);
					System.out.println("Cliente : "+each.getCustomer().getUsername());
				});
			System.out.println("");

			System.out.println("\nPROBANDO RELACIONES BIDIRECCIONALES Customer --> Direccion");
			customerCrudRepository.findAll()
					.forEach(customer->{
						System.out.println("Customer: "+ customer);
						System.out.println("Direcciones asociadas: "+listarDirecciones(customer.getAdresses()));
					});
			System.out.println("");
		};
	}

	private String listarDirecciones(List<Adress> adresses) {
		String lis = "";
		for(Adress ad : adresses){
			lis += ad.toString();
		}
		return lis;
	}
}
