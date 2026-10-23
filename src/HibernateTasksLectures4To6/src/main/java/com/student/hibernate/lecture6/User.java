package com.student.hibernate.lecture6;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "L6User")
@Table(name = "l6_users")
public class User {

    @Id
    private Long id;
    private String name;
    private Integer age;

    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private UserDetails userDetails;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "l6_user_friends",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id")
    )
    private List<Friend> friends = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Post> posts = new ArrayList<>();

    public User() {
    }

    public User(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public void setUserDetails(UserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public void addFriend(Friend friend) {
        friends.add(friend);
        friend.getUsers().add(this);
    }

    public void addPost(Post post) {
        posts.add(post);
        post.setUser(this);
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }
}

