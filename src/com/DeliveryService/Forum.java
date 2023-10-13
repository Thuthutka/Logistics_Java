package com.DeliveryService;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
public class Forum implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String forumName;
    private String forumDescription;
    @OneToMany(mappedBy = "forum", cascade = CascadeType.ALL, orphanRemoval = true, fetch=FetchType.EAGER)
    private List<Comment> comments;

    public Forum(String forumName, String forumDescription) {
        this.forumName = forumName;
        this.forumDescription = forumDescription;
    }

    @Override
    public String toString() {
        return forumName + " ( " + forumDescription+" )";
    }
}