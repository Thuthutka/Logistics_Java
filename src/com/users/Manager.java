package com.users;

import com.DeliveryService.Delivery;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Manager extends User implements Serializable {

    @OneToMany
    private List<Delivery> myDeliveries;

    private String officeAdress;

    private boolean isAdmin;

    public Manager(String name, String surname, String username, String password, boolean isManager) {
        super(name, surname, username, password, isManager);
    }
}
