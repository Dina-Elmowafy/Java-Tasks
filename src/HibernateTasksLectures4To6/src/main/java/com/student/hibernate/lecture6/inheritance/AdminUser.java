package com.student.hibernate.lecture6.inheritance;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity(name = "L6AdminUser")
@DiscriminatorValue("ADMIN")
public class AdminUser extends BaseUser {

    private String permissionName;

    public AdminUser() {
    }

    public AdminUser(Long id, String name, String permissionName) {
        super(id, name);
        this.permissionName = permissionName;
    }
}

