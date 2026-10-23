package com.student.hibernate.lecture6.eager;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name = "L6EagerUserDetails")
@Table(name = "l6_eager_user_details")
public class EagerUserDetails {

    @Id
    private Long id;
    private String address;
    private String phone;

    @OneToOne(mappedBy = "userDetails")
    private EagerUser user;

    public EagerUserDetails() {
    }
}

