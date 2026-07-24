package com.student.hibernate.lecture6.eager;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name = "L6EagerUser")
@Table(name = "l6_eager_users")
public class EagerUser {

    @Id
    private Long id;
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    private EagerUserDetails userDetails;

    public EagerUser() {
    }

    public EagerUserDetails getUserDetails() {
        return userDetails;
    }
}

