package com.DeliveryService;

import com.DeliveryService.Enums.OrderSize;
import com.DeliveryService.Enums.OrderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int Id;

    private String name;
    private String clientName;

    private Double weight;

    private String orderType;
    private String orderSize;

    private String size;

    @OneToOne()
    private Delivery delivery;

    public Cargo(String name, String clientName, Double weight) {
        this.name = name;
        this.clientName = clientName;
        this.weight = weight;
    }
    public Cargo(String name, String clientName, Double weight, String orderType, String orderSize, String size, Delivery delivery) {
        this.name = name;
        this.clientName = clientName;
        this.weight = weight;
        this.orderType = orderType;
        this.orderSize = orderSize;
        this.size = size;
        this.delivery = delivery;
    }

    @Override
    public String toString() {
        return "Cargo{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", clientName='" + clientName + '\'' +
                '}';
    }
}
