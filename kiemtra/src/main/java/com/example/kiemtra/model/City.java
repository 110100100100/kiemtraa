package com.example.kiemtra.model;

import javax.persistence.*;

@Entity
@Table(name="city")
public class City {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    private String nameCity;
    private String national;

    public City(Long id, String nameCity, String national) {
        this.id = id;
        this.nameCity = nameCity;
        this.national = national;
    }

    public City() {
    }

    public String getNameCity() {
        return nameCity;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public String getNational() {
        return national;
    }

    public void setNational(String national) {
        this.national = national;
    }



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
