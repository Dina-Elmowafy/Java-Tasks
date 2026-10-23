package com.student.hibernate.lecture6;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "L6Friend")
@Table(name = "l6_friends")
public class Friend {

    @Id
    private Long id;
    private String name;

    @ManyToMany(mappedBy = "friends")
    private List<User> users = new ArrayList<>();

    public Friend() {
    }

    public Friend(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }
}

