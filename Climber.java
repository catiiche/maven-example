package jpa.entity.coursework4;

import jpa.entity.Group;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Climber {

    @Id
    @Column(nullable = false, length = 20)
    private String name;

    @Column(nullable = false, length = 40)
    private String address;

    @Column(nullable = false, length = 3)
    private int age;

    @ManyToOne (optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "groupOfClimbers")
    GroupForClimbing group;


    public Climber(String name, String address, int age) {
        setName(name);
        setAddress(address);
        setAge(age);
    }

    public Climber() {
    }

    public void setName(String name) {
        if (name == null || name.length() < 3)
            throw new IllegalArgumentException("name должен быть не меньше 3");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.length() < 5)
            throw new IllegalArgumentException("address должен быть не меньше 5");
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age <= 18)
            throw new IllegalArgumentException("age должен быть больше 18");
        this.age = age;
    }

    public GroupForClimbing getGroup() {
        return group;
    }

    public void setGroup(GroupForClimbing group) {
        this.group = group;
    }
}