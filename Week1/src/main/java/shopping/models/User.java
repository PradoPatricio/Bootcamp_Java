package shopping.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;
    @Column
    private String name;
    @OneToMany
    private List<Cart> userCarts;

    public User() {
        this.userCarts=new ArrayList<Cart>();
    }

    public User(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "(Name: " + this.name + ")";
    }

    @Override
    public boolean equals(Object user) {
        User u = (User) user;
        return (u.getId() == this.id);
    }
}