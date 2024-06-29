package com.bycompany.persistence.entity;

import jakarta.persistence.*;

@Entity(name = "direcciones")
public class Adress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "pais")
    private String country;

    @Column(name = "direccion")
    private String adress;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    Customer customer;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Adress{" +
                "adress='" + adress + '\'' +
                ", country='" + country + '\'' +
                ", id=" + id +
                '}';
    }
}
