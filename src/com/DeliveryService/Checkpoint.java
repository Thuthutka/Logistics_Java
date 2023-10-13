package com.DeliveryService;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

@Entity
public class Checkpoint implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String location;
    private int stopedTime;
    @ManyToOne
    private Delivery delivery;

    public Checkpoint(String location, int stopedTime, Delivery delivery) {
        this.location = location;
        this.stopedTime = stopedTime;
        this.delivery = delivery;
    }
}
