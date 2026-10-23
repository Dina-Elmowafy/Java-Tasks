package com.student.hibernate.lecture4.task1;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "l4_players")
public class Player {

    @Id
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 10)
    private String age;

    private boolean status;

    public Player() {
    }

    public Player(Long id, String name, String age, boolean status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

