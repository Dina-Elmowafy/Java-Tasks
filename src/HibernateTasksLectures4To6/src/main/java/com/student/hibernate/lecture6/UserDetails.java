package com.student.hibernate.lecture6;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity(name = "L6UserDetails")
@Table(name = "l6_user_details")
public class UserDetails {

    @Id
    private Long id;
    private String address;
    private String phone;

    @OneToOne(mappedBy = "userDetails")
    private User user;

    public UserDetails() {
    }

    public UserDetails(Long id, String address, String phone) {
        this.id = id;
        this.address = address;
        this.phone = phone;
    }
}

