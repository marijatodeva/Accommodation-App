package com.example.accommodationapp.model.domain;

import com.example.accommodationapp.model.enumerations.Category;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Accommodation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;

    @Enumerated(EnumType.STRING)
    Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    Host host;

//    @OneToMany
//    List<Review> reviewList;

    Integer numRooms;

    boolean isRented = false;

    public Accommodation() {
    }

    public Accommodation(String name, Category category, Host host, Integer numRooms) {
        this.name = name;
        this.category = category;
        this.host = host;
        this.numRooms = numRooms;
        this.isRented = false;
//        this.reviewList=new ArrayList<>();
//        this.reviewList.add(reviewList);
    }

//    public List<Review> getReviewList() {
//        return reviewList;
//    }

//    public void setReviewList(List<Review> reviewList) {
//        this.reviewList = reviewList;
//    }

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public Integer getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(Integer numRooms) {
        this.numRooms = numRooms;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }
}
