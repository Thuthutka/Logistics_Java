package com.users;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String surname;
    @Column(unique = true)
    private String username;
    private String password;

    private String email;

    private LocalDate birthday;



    private boolean isManager;

    public User(String name, String surname, String username, String password, boolean isManager) {
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.isManager = isManager;
    }

    @Override
    public String toString() {
        return "Name: " + name + " Surname: " + surname;
    }
}
