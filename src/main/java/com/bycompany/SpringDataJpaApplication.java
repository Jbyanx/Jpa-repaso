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

import java.util.List;

@SpringBootApplication
public class SpringDataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaApplication.class, args);
	}

	@Autowired
	private CustomerCrudRepository customerCrudRepository;
	@Bean
	public CommandLineRunner testOneToOneRelationshipCommand (){
		return args -> {
			Customer juan = new Customer();
			juan.setName("Juan perez");
			juan.setPassword("juan123");
			juan.setUsername("juan123");

			Adress juanAdress = new Adress();
			juanAdress.setCountry("Colombia");
			juanAdress.setAdress("Manzana O casa 15, Timayui 2, Santa Marta, Magdalena");

			juan.setAdress(juanAdress);

			Customer jose = new Customer();
			jose.setName("Jose ortiz");
			jose.setPassword("joselo");
			jose.setUsername("juooo");

			Adress joseAdress = new Adress();
			joseAdress.setCountry("Brasil");
			joseAdress.setAdress("calle 1a barrio la libertad");

			jose.setAdress(joseAdress);

			Customer maria = new Customer();
			maria.setName("maria toloza");
			maria.setPassword("Mariuwu");
			maria.setUsername("Mariuwu");

			Adress mariaAdress = new Adress();
			mariaAdress.setCountry("chile");
			mariaAdress.setAdress("Casa 34 colonia morelos");
			maria.setAdress(mariaAdress);

			List<Customer> customerList = List.of(juan, jose,maria);
			//customerCrudRepository.saveAll(customerList);
		};
	}
	@Bean
	public CommandLineRunner testAdressCrudRepositoryCommand (AdressCrudRepository adressCrudRepository){
		return args -> {
			adressCrudRepository.findAll()
					.forEach(each -> {
						System.out.println(each.getAdress()+" - "+each.getCustomer().getUsername());
					});
		};
	}
}
