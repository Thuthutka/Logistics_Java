package com.DeliveryService;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Vehicle implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String model;
    private LocalDate year = LocalDate.parse("1999-01-01");
    private String licenceNumber;
    private String fuelType;

    @OneToOne
    private Delivery delivery;

    public Vehicle(String model, String licenceNumber, LocalDate year, String fuelType) {
        this.model = model;
        this.year = year;
        this.licenceNumber = licenceNumber;
        this.fuelType = fuelType;
    }

    @Override
    public String toString() {
        return "ID=" + id +
                ", Model=" + model +
                ", Year=" + year +
                ", Licence number=" + licenceNumber +
                ", Fuel type=" + fuelType;
    }
}