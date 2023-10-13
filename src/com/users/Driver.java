package com.users;

import com.DeliveryService.Delivery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Driver extends User implements Serializable {

    @OneToMany
    private List<Delivery> myDeliveries;

    public Driver(String name, String surname, String username, String password, boolean isManager) {
        super(name,surname,username,password,isManager);
    }
}
