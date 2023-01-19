package com.example.springmultipledatasource.slave.entity;


import javax.persistence.*;

@Entity
@Table(name = "product")
public class ProductSlave {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private Long price ;

    public ProductSlave(Long id, String name, Long price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public ProductSlave() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
