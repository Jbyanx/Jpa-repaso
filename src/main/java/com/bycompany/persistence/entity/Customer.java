package com.bycompany.persistence.entity;

import com.bycompany.exceptions.AdressNotFoundException;
import com.bycompany.persistence.repository.CustomerCrudRepository;
import jakarta.persistence.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "usuario", unique = true)
    private String username;


    @OneToMany(fetch = FetchType.EAGER, mappedBy = "", cascade = CascadeType.PERSIST)
    private List<Adress> adresses;

    @Column(name = "contrasena")
    private String password;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Adress> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<Adress> adresses) {
        this.adresses = adresses;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public void addAdress(Adress newAdress) throws AdressNotFoundException {
        if(newAdress == null) {
            throw new AdressNotFoundException("Adress is null");
        }
        if(this.adresses == null)
            this.adresses = new ArrayList<>();

        this.adresses.add(newAdress);
        newAdress.setCustomer(this);
    }

    @Transactional
    public void guardarConDirecciones(CustomerCrudRepository customerCrudRepository, Adress mariaAdress1, Adress mariaAdress2) throws AdressNotFoundException {
        this.addAdress(mariaAdress1);
        this.addAdress(mariaAdress2);

        customerCrudRepository.save(this);
    }
}
