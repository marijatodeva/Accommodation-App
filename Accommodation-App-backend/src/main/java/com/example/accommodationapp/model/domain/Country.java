package com.example.accommodationapp.model.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String continent;

    public Country() {
    }

    public Country(String name, String continent) {
        this.name = name;
        this.continent = continent;
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

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }
}
