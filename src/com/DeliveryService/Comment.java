package com.DeliveryService;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@AllArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

@Entity
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String creatorsUsername;
    private LocalDateTime postDate;
    private String commentText;
    @ManyToOne
    private Forum forum;
    @OneToMany(mappedBy = "parentComment", orphanRemoval = true, fetch=FetchType.EAGER)
    private List<Comment> replies;
    @ManyToOne
    private Comment parentComment;

    public Comment(){
        this.postDate = LocalDateTime.now();
    }

    public Comment(String creatorsUsername, String content, Forum forum) {
        this.postDate = LocalDateTime.now();
        this.creatorsUsername = creatorsUsername;
        this.commentText = content;
        this.replies = new ArrayList<>();
        this.forum = forum;
    }

    public Comment(String creatorsUsername, String content, Comment parentComment) {
        this.postDate = LocalDateTime.now();
        this.creatorsUsername = creatorsUsername;
        this.commentText = content;
        this.replies = new ArrayList<>();
        this.parentComment = parentComment;
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd |HH:mm|");
        return postDate.format(formatter)+" "+ creatorsUsername +" : "+ commentText;
    }
}
