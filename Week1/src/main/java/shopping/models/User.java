package shopping.models;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;
    private String name;

    public User() {
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