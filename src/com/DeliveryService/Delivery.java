package com.DeliveryService;

import com.DeliveryService.Enums.*;
import com.users.Driver;
import com.users.Manager;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Delivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private LocalDate creationDate;
    private LocalDate deliveryDate;
    private String name;
    private String deliveryStatus;

    private String deliveryStartLocation;
    private String deliveryEndLocation;

    @OneToMany(mappedBy = "delivery", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Checkpoint> checkpoints;
    @ManyToOne
    private Driver driver;

    @ManyToOne
    private Manager responsiblePerson;


    @OneToOne()
    private Cargo cargo;
    @OneToOne
    private Vehicle vehicle;

    @Override
    public String toString() {
        return name + " status: "  + deliveryStatus;
    }

    public Delivery(String name, Driver driver) {
        this.name = name;
        this.driver = driver;
    }

    public String getDriverUsername() {
        return driver.getUsername();
    }

    public Delivery(LocalDate creationDate, LocalDate deliveryDate, String name, String deliveryStartLocation, String deliveryEndLocation, Driver driver, Cargo cargo, Vehicle vehicle) {
        this.creationDate = creationDate;
        this.deliveryDate = deliveryDate;
        this.name = name;
        this.deliveryStartLocation = deliveryStartLocation;
        this.deliveryEndLocation = deliveryEndLocation;
        this.driver = driver;
        this.cargo = cargo;
        this.vehicle = vehicle;
    }

    public Delivery(LocalDate creationDate, LocalDate deliveryDate, String name, String deliveryStatus, String deliveryStartLocation,
                    String deliveryEndLocation, Driver driver, Cargo cargo, Vehicle vehicle) {
        this.creationDate = creationDate;
        this.deliveryDate = deliveryDate;
        this.name = name;
        this.deliveryStatus = deliveryStatus;
        this.deliveryStartLocation = deliveryStartLocation;
        this.deliveryEndLocation = deliveryEndLocation;
        this.driver = driver;
        this.cargo = cargo;
        this.vehicle = vehicle;
    }
}
