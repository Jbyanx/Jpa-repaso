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
	public CommandLineRunner populateDatabaseCommand(){
		return args -> {
			Customer pedro = new Customer();
			pedro.setName("pedro marquez");
			pedro.setUsername("pedrinhoe");
			pedro.setPassword("pelloski");

			Adress pedroAdress1 = new Adress();
            pedroAdress1.setCountry("Colombia");
            pedroAdress1.setAdress("barrio la chinita, barranquilla, atlantico");
			pedro.addAdress(pedroAdress1);

			Customer luis = new Customer();
			luis.setName("luis diaz marquez");
			luis.setUsername("luchine");
			luis.setPassword("luisDi123");

			Adress luisAdress1 = new Adress();
            luisAdress1.setCountry("Colombia");
            luisAdress1.setAdress("barrio el bosque, barrancas, La Guajira");
			luis.addAdress(luisAdress1);

			//customerCrudRepository.saveAll(List.of(luis, pedro));
		};
	}

	@Bean
	@Order(2)
	public CommandLineRunner testQueryMethodAndJPQLExamplesCommand(AdressCrudRepository adressCrudRepository){
		return args -> {
            customerCrudRepository.findCustomersFrom("canada")
                    .forEach(canada -> {
                        System.out.println("\nCustomer con direccion canada JPQL");
                        System.out.println("Customer: "+ canada);
                        System.out.println("Direcciones en cuestion: "+ canada.getAdresses());
                    });
            System.out.println();

//            customerCrudRepository.findDistinctByAddressesCountry("Colombia")
//                    .forEach(colombiano -> {
//                        System.out.println("\nCustomer con direccion canada QUERY METHOD");
//                        System.out.println("Customer: "+ colombiano);
//                        System.out.println("Direcciones en cuestion: "+ colombiano.getAdresses());
//                    });
//            System.out.println();

            adressCrudRepository.findAdressByCustomerNameEndingWithIgnoreCase("re")
                    .forEach(ez ->{
                        System.out.println("\nDirecciones con customer name que terminen en 're'");
                        System.out.println(ez);
                        System.out.println("nombre: "+ez.getCustomer().getName());
                    });
		};
	}

}
