package com.example.ecomerce_application.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private int id;
    private String name;
    private String description;
    private String brand;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date releaseDate;
    private BigDecimal price;
    private int stockQuantity;
    private String category;
    private boolean productAvailable;

    private String ImageName;
    private String ImageType;
    @Lob // larg object
    private byte[] imageData; // store in the DB

}
