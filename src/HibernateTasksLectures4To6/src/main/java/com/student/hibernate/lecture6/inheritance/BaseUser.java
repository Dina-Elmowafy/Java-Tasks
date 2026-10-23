package com.student.hibernate.lecture6.inheritance;

import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@Entity(name = "L6BaseUser")
@Table(name = "l6_inheritance_users")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_type")
public class BaseUser {

    @Id
    private Long id;
    private String name;

    public BaseUser() {
    }

    public BaseUser(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}

