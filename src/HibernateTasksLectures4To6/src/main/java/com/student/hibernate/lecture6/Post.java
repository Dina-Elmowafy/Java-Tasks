package com.student.hibernate.lecture6;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "L6Post")
@Table(name = "l6_posts")
public class Post {

    @Id
    private Long id;
    private String header;
    private String content;

    @ManyToOne
    private User user;

    public Post() {
    }

    public Post(Long id, String header, String content) {
        this.id = id;
        this.header = header;
        this.content = content;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

