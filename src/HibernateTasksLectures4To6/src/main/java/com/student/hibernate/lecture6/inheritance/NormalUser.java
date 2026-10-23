package com.student.hibernate.lecture6.inheritance;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "L6NormalUser")
@DiscriminatorValue("NORMAL")
public class NormalUser extends BaseUser {

    private Integer points;

    public NormalUser() {
    }

    public NormalUser(Long id, String name, Integer points) {
        super(id, name);
        this.points = points;
    }
}

